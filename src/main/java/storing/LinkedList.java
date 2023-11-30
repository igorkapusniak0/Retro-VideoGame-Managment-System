package storing;

import java.io.Serializable;
import java.util.Comparator;

public class LinkedList<T extends Comparable<T>> implements Serializable {
    public Node<T> head;
    public LinkedList(){
        this.head = null;
    }
    public void add(T data){
        Node<T> newNode = new Node<>(data);
        if (head == null){
            head = newNode;
        }
        else {
            Node<T> current = head;
            while (current.next != null){
                current=current.next;
            }
            current.next = newNode;
        }
    }

    public void remove(T data) {
        if (head == null) {
            return;
        }
        if (head.data.equals(data)) {
            head = head.next;
            return;
        }
        Node<T> current = head;
        while (current.next != null && !current.next.data.equals(data)) {
            current = current.next;
        }
        if (current.next != null) {
            current.next = current.next.next;
        }
    }

    public boolean isEmpty(){
        return head ==null;
    }
    public boolean contains(T data) {
        Node<T> current = head;
        while (current != null) {
            if (current.data.equals(data)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }


    public int size() {
        Node<T> current = head;
        int size=0;
        while (current != null) {
            size+=1;
            current = current.next;
        }
        return size;
    }
    public <E> T get(int index){
        Node<T> current = head;
        int i=0;
        while (current != null&&i!=index) {
            i+=1;
            current = current.next;
        }
        return current.data;
    }
    public String display(){
        Node<T> current = head;
        String s = "";
        int i =0;
        while (current !=null){
            s += i + ". "+ current.data+ ", ";
            current=current.next;
            i+=1;
        }
        return s;
    }



    public static <T> Node<T> quickSortRec(Node head, Comparator<T> comparator) {
        if (head == null || head.next == null) {
            return head;
        }

        Node<T> mid = findMiddle(head);
        Node<T> nextOfMid = mid.next;
        mid.next = null;

        Node<T> left = quickSortRec(head, comparator);
        Node<T> right = quickSortRec(nextOfMid, comparator);

        return merge(left, right, comparator);
    }

    // Helper method to find the middle node of the list
    public static <T extends Comparable<T>> Node<T> findMiddle(Node<T> node) {
        if (node == null) {
            return null;
        }
        Node<T> slow = node;
        Node<T> fast = node.next;

        while (fast != null) {
            fast = fast.next;
            if (fast != null) {
                slow = slow.next;
                fast = fast.next;
            }
        }
        return slow;
    }

    // Helper method to merge two sorted linked lists
    public static <T> Node<T> merge(Node<T> left, Node<T> right, Comparator<T> comparator) {
        Node<T> result = null;

        if (left == null) {
            return right;
        }
        if (right == null) {
            return left;
        }

        if (comparator.compare(left.data, right.data) <= 0) {
            result = left;
            result.next = merge(left.next, right, comparator);
        } else {
            result = right;
            result.next = merge(left, right.next, comparator);
        }

        return result;
    }


}

