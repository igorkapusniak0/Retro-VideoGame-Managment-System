package storing;

import java.io.Serializable;

public class LinkedList<T> implements Serializable {
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

}

