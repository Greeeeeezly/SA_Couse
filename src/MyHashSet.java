import java.util.Iterator;

public class MyHashSet<T> implements Iterable<T> {
    private MyHashTable<T, Object> hashTable;

    public MyHashSet() {
        hashTable = new MyHashTable<>();
    }


    public void add(T value) {
        if (!hashTable.containsKey(value)) {
            hashTable.add(value, new Object());
        }
    }

    public void remove(T value) {
        hashTable.remove(value);
    }

    public boolean contains(T value) {
        return hashTable.containsKey(value);
    }

    public int size() {
        return hashTable.size();
    }

    @Override
    public Iterator<T> iterator() {
        MyLinkedList<T> keys = new MyLinkedList<>();
        for (KeyValue<T, Object> keyValue : hashTable) {
            keys.addLast(keyValue.getKey());
        }
        return keys.iterator();
    }
}
