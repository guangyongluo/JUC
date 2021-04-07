package com.vilin.collections;

public class PriorityLinkedList<E extends Comparable<E>> {
    private Node<E> first;

    private final Node<E> NULL = (Node<E>) null;

    private final static String PLAIN_NULL = "null";

    private int size;

    public PriorityLinkedList() {
        this.first = NULL;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public static <E extends Comparable<E>> PriorityLinkedList<E> of(E... elements) {
        final PriorityLinkedList list = new PriorityLinkedList<>();

        if (elements.length != 0) {
            for (E ele : elements) {
                list.addFirst(ele);
            }
        }

        return list;
    }

    public PriorityLinkedList<E> addFirst(E e) {
        final Node<E> newNode = new Node<>(e);
        Node<E> previous = NULL;
        Node<E> current = first;
        while (null != current && e.compareTo(current.value) > 0) {
            previous = current;
            current = current.next;
        }

        if (previous == NULL) {
            first = newNode;
        }else{
            previous.next = newNode;
        }
        newNode.next = current;

        size++;
        return this;
    }

    public boolean contains(E e) {
        Node<E> current = first;
        while (null != first) {
            if (current.value == e) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    static class NoElementException extends RuntimeException {
        public NoElementException(String message) {
            super(message);
        }
    }

    public E removeFirst() {
        if (this.isEmpty()) {
            throw new LinkedList.NoElementException("The linked list is empty.");
        }

        Node<E> node = first;
        first = node.next;
        this.size--;
        return node.value;
    }

    public String toString() {
        if (this.isEmpty()) {
            return "[]";
        } else {
            StringBuilder builder = new StringBuilder("[");
            Node<E> current = first;
            while (current != null) {
                builder.append(current.toString()).append(",");
                current = current.next;
            }
            builder.replace(builder.length() - 1, builder.length(), "");
            builder.append("]");
            return builder.toString();
        }
    }

    private static class Node<E> {
        E value;
        Node<E> next;

        public Node(E value) {
            this.value = value;
        }

        @Override
        public String toString() {
            if (null != value) {
                return value.toString();
            }
            return "null";
        }
    }

    public static void main(String[] args) {

    }
}
