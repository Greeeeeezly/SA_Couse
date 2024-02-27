import java.util.Iterator;

public class MyLinkedList<Type> implements Iterable<Type>{
    private int size=0;
    private Node<Type> head;
    private Node<Type> tail;


    private boolean isEmpty(){
        return head==null;
    }
    public void printAll(){
        Node nodeForIteration = head;
        while (nodeForIteration!=null){
            System.out.println(nodeForIteration.value);
            nodeForIteration=nodeForIteration.next;
        }
    }

    public int getSize() {
        return size;
    }

    public void printSize(){
        System.out.println(size);
    }
    public void addFirst(Type value){
        Node<Type> fistNode=new Node<>(value);
        if (isEmpty()){
            tail=fistNode;
        }
        else {
            head.prev=fistNode;
        }
        fistNode.next=head;
        head=fistNode;
        size++;
    }
    public void addLast(Type value){
        Node<Type> lastNode=new Node<>(value);
        if (isEmpty()){
            head=lastNode;
        }
        else {
            tail.next=lastNode;
        }
        lastNode.prev=tail;
        tail=lastNode;
        size++;
    }
    public void addByIndex(Type value, int index) {
        if (index < 0)
            throw new IllegalArgumentException("Индекс не может быть отрицательным: " + index);

        Node<Type> newNode = new Node<>(value);

        if (index == 0) {
            newNode.next = head;
            head = newNode;
        } else {
            Node<Type> prevNode = getNodeAtIndex(index - 1);

            if (prevNode == null) {
                throw new IndexOutOfBoundsException("Индекс выходит за пределы списка: " + index);
            }

            newNode.next = prevNode.next;
            newNode.prev = prevNode;
            prevNode.next = newNode;

            if (newNode.next != null) {
                newNode.next.prev = newNode;
            }

        }
        size++;
    }
    public Node<Type> getNodeAtIndex(int index) {
        Node<Type> nodeForIteration = head;
        int currentIndex = 0;

        while (nodeForIteration != null && currentIndex < index) {
            nodeForIteration = nodeForIteration.next;
            currentIndex++;
        }

        return nodeForIteration;
    }


    public void deleteFirst(){
        if (isEmpty()){}
        else {
            head=head.next;
            head.prev=null;
            size--;
        }
    }
    public void deleteLast(){
        if (isEmpty()){}
        else {
            tail=tail.prev;
            tail.next=null;
            size--;
        }
    }
    public boolean contains(Type value) {
        Node<Type> nodeForIteration = head;

        while (nodeForIteration != null) {
            if (nodeForIteration.value.equals(value)) {
                return true;
            }
            nodeForIteration = nodeForIteration.next;
        }

        return false;
    }
    public void deleteByIndex(int index) {
        Node<Type> nodeForIteration = head;
        int number = 0;

        if (isEmpty()) {
            return;
        }


        while (nodeForIteration != null) {
            if (number == index) {
                size--;
                if (nodeForIteration.prev != null) {
                    nodeForIteration.prev.next = nodeForIteration.next;
                } else {
                    head = nodeForIteration.next;
                }

                if (nodeForIteration.next != null) {
                    nodeForIteration.next.prev = nodeForIteration.prev;
                } else {
                    tail = nodeForIteration.prev;
                }

                return;
            }
            nodeForIteration = nodeForIteration.next;
            number += 1;
        }
    }

    public void updateAllDuplicates(Type prevValue, Type newValue){
        Node<Type> nodeForIteration=head;
        if (isEmpty()){
        }
        else {
            while (nodeForIteration.next!=null){
                if(nodeForIteration.value==prevValue){
                    nodeForIteration.value=newValue;
                }
                nodeForIteration=nodeForIteration.next;
            }
        }
    }
    public void updateByIndex(int index, Type newValue){
        Node<Type> nodeForIteration=head;
        int number=0;
        if (isEmpty()){}
        else {
            while (nodeForIteration!=null){
                if(number==index){
                    nodeForIteration.value=newValue;
                }
                nodeForIteration=nodeForIteration.next;
                number++;
            }
        }
    }
    public void deleteByValue(Type value) {
        Node<Type> nodeForIteration = head;

        while (nodeForIteration != null) {
            if (nodeForIteration.value.equals(value)) {
                size--;

                if (nodeForIteration.prev != null) {
                    nodeForIteration.prev.next = nodeForIteration.next;
                } else {
                    head = nodeForIteration.next;
                }

                if (nodeForIteration.next != null) {
                    nodeForIteration.next.prev = nodeForIteration.prev;
                } else {
                    tail = nodeForIteration.prev;
                }

                return;
            }
            nodeForIteration = nodeForIteration.next;
        }
    }


    @Override
    public Iterator<Type> iterator() {
        return new LinkedListIterator();
    }

    private class LinkedListIterator implements Iterator<Type> {
        private Node<Type> current = head;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Type next() {
            if (!hasNext()) {
                return null;
            }
            Type value = current.value;
            current = current.next;
            return value;
        }
    }

    public static class Node<Type>{
        public Type getValue() {
            return value;
        }

        private Type value;

        private Node<Type> next;

        private Node<Type> prev;

        @Override
        public String toString() {
            return "Node{" +
                    "value=" + value +
                    ", next=" + next +
                    ", prev=" + prev +
                    '}';
        }

        public Node(Type value) {
            this.value = value;
        }

    }
}
