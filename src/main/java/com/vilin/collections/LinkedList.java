package com.vilin.collections;

public class LinkedList<E> {

    private Node<E> first;

    private final Node<E> last = (Node<E>) null;

    private final static String PLAIN_NULL = "null";

    private int size;

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return size() == 0;
    }

    public static <E> LinkedList<E> of(E... elements){
        final LinkedList list = new LinkedList();

        if(elements.length != 0){
            for(E ele : elements){
                list.addFirst(ele);
            }
        }

        return list;
    }

    public LinkedList<E> addFirst(E e){
        final Node<E> newNode = new Node<>(e);
        newNode.next = first;
        this.size++;
        this.first = newNode;
        return this;
    }

    public boolean contains(E e){
        Node<E> current = first;
        while(null != first){
            if(current.value == e){
                return true;
            }
            current = current.next;
        }
        return false;
    }

    static class NoElementException extends RuntimeException{
        public NoElementException(String message) {
            super(message);
        }
    }

    public E removeFirst(){
        if(this.isEmpty()){
            throw new NoElementException("The linked list is empty.");
        }

        Node<E> node = first;
        first = node.next;
        this.size--;
        return node.value;
    }

    public String toString(){
        if(this.isEmpty()){
            return "[]";
        }else{
            StringBuilder builder = new StringBuilder("[");
            Node<E> current = first;
            while(current != null){
                builder.append(current.toString()).append(",");
                current = current.next;
            }
            builder.replace(builder.length() - 1, builder.length(), "");
            builder.append("]");
            return builder.toString();
        }
    }

    private static class Node<E>{
        E value;
        Node<E> next;

        public Node(E value){
            this.value = value;
        }

        @Override
        public String toString() {
            if(null != value){
                return value.toString();
            }
            return "null";
        }
    }

    public static void main(String[] args) {
        LinkedList<String> list = LinkedList.of("hello", "world", "Scala", "Java", "Thread");
        list.addFirst("Concurrency").addFirst("Test");
        System.out.println(list.size());
        System.out.println(list.contains("Scala"));
        System.out.println("===============================");
        System.out.println(list.toString());
        while(!list.isEmpty()){
            System.out.println(list.removeFirst());
        }
        System.out.println(list.size());
        System.out.println(list.isEmpty());
    }
}
