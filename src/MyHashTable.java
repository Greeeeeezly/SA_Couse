
import java.util.Iterator;

public class MyHashTable<K, V> implements Iterable<KeyValue<K, V>> {
    public MyLinkedList<KeyValue<K, V>>[] slots;
    private int count;
    private static final int IC = 16;
    private static final double LF = 0.75d;
    public MyHashTable() {
        this(IC);
    }
    public MyHashTable(int capacity) {
        this.slots = new MyLinkedList[capacity];
        this.count = 0;
    }
    public void add(K key, V value) {
        growIfNeeded();
        int slotNumber = findSlotNumber(key);
        if (this.slots[slotNumber] == null) {
            this.slots[slotNumber] = new MyLinkedList<>();
        }
        for (KeyValue<K, V> element : this.slots[slotNumber]) {
            if (element.getKey().equals(key)) {
                return;
            }
        }
        KeyValue<K, V> kvp = new KeyValue<>(key, value);
        this.slots[slotNumber].addLast(kvp);
        this.count++;
    }
    private int findSlotNumber(K key) {
        return Math.abs(key.hashCode()) % this.slots.length;
    }
    private void growIfNeeded() {
        if ((double)(this.count + 1) / this.slots.length > LF) {
            grow();
        }
    }
    private void grow() {
        MyHashTable<K, V> newHT = new MyHashTable<>(2 * this.slots.length);
        for (KeyValue<K, V> element : this) {
            newHT.add(element.getKey(), element.getValue());
        }
        this.slots = newHT.slots;
        this.count = newHT.count;
    }
    public int size() {
        return this.count;
    }
    public int capacity() {
        return this.slots.length;
    }
    public boolean addOrReplace(K key, V value) {
        int slotNumber = findSlotNumber(key);
        if (this.slots[slotNumber] != null) {
            for (KeyValue<K, V> element : this.slots[slotNumber]) {
                if (element.getKey().equals(key)) {
                    element.setValue(value);
                    return true;}}}
        this.add(key, value);
        return false;
    }
    public V get(K key) {
        KeyValue<K, V> kv = find(key);
        if (kv == null) {
            return null;
        }
        return kv.getValue();
    }
    public KeyValue<K, V> find(K key) {
        int slotNumber = findSlotNumber(key);
        MyLinkedList<KeyValue<K, V>> elements = this.slots[slotNumber];
        if (elements != null) {
            for (KeyValue<K, V> element : elements) {
                if (element.getKey().equals(key)) {
                    return element;}}}
        return null;
    }
    public boolean containsKey(K key) {
        return find(key) != null;
    }
    public boolean remove(K key) {
        int slotNumber = findSlotNumber(key);
        MyLinkedList<KeyValue<K, V>> elements = this.slots[slotNumber];
        if (elements != null) {
            for (KeyValue<K, V> element : elements) {
                if (element.getKey().equals(key)) {
                    elements.deleteByValue(element);
                    this.count=count-1;
                    return true;}
            }
        }
        return false;
    }
    public void clear() {
        this.slots = new MyLinkedList[this.slots.length];
        this.count = 0;
    }
    public Iterable<K> keys() {
        MyLinkedList<K> keys = new MyLinkedList<>();
        for (KeyValue<K, V> kv : this) {
            keys.addLast(kv.getKey());
        }
        return keys;
    }
    public Iterable<V> values() {
        MyLinkedList<V> values = new MyLinkedList<>();
        for (KeyValue<K, V> kv : this) {
            values.addLast(kv.getValue());}
        return values;
    }
    @Override
    public Iterator<KeyValue<K, V>> iterator() {
        MyLinkedList<KeyValue<K, V>> elements = new MyLinkedList<>();
        for (MyLinkedList<KeyValue<K, V>> slot : this.slots) {
            if (slot != null) {
                for (KeyValue<K, V> kv : slot) {
                    elements.addLast(kv);}}}
        return elements.iterator();}}
