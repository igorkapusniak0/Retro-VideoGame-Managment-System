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

    Node<T> partitionLast(Node<T> start, Node<T> end, Comparator<T> comparator) {
        if (start == end || start == null || end == null)
            return start;

        Node<T> pivotPrev = start;
        Node<T> curr = start;
        T pivot = end.data;

        while (start != end) {
            if (comparator.compare(start.data, pivot) < 0) {
                pivotPrev = curr;
                T temp = curr.data;
                curr.data = start.data;
                start.data = temp;
                curr = curr.next;
            }
            start = start.next;
        }

        T temp = curr.data;
        curr.data = pivot;
        end.data = temp;

        return pivotPrev;
    }

    void sort(Node<T> start, Node<T> end, Comparator<T> comparator) {
        if (start == null || start == end || start == end.next)
            return;

        Node<T> pivotPrev = partitionLast(start, end, comparator);
        sort(start, pivotPrev, comparator);

        if (pivotPrev != null && pivotPrev == start)
            sort(pivotPrev.next, end, comparator);
        else if (pivotPrev != null && pivotPrev.next != null)
            sort(pivotPrev.next.next, end, comparator);
    }

    public void sort(Comparator<T> comparator) {
        Node<T> lastNode = head;
        while (lastNode.next != null) {
            lastNode = lastNode.next;
        }
        sort(head, lastNode, comparator);
    }


}

