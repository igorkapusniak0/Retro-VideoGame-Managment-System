package storing;
import Controllers.API;

import java.io.Serializable;
import java.util.Scanner;

public class Hashing<T extends Comparable<T>> implements Serializable {
    public LinkedList<T>[] hashTable;

    public Hashing(int size){
        hashTable = (LinkedList<T>[]) new LinkedList [size];
        for (int i=0;i<size;i++){
            hashTable[i]=new LinkedList<>();
        }
    }
    public int hashFunction(int key){
        return ((((int)(key/10.0)*10)-1950)/10);
    }

    //TODO
    //Change to voids after testing
    public int add(T data, int key){
        int home = hashFunction(key);
        System.out.println(home);
        hashTable[home].add(data);
        System.out.println(home);
        return home;
    }
    public <T extends Comparable<T>> T getItem(T data, int key) {
        int hashIndex = hashFunction(key);
        LinkedList<T> list = (LinkedList<T>) hashTable[hashIndex];
        Node<T> current = list.head;
        while (current != null) {
            if (current.data.equals(data)) {
                return current.data;
            }
            current = current.next;
        }
        return null;
    }

    public int remove(T data){
        int home = hashFunction(Math.abs(data.hashCode()));
        for (int i = 0; i < hashTable.length; i++){
            hashTable[i].remove(data);
        }
        System.out.println(home);
        return home;
    }
    public void clearHashTable() {
        for (int i = 0; i < hashTable.length; i++) {
            hashTable[i].head=null;
        }
    }
    public void display() {
        for (int i = 0; i < hashTable.length; i++) {
            LinkedList<T> list = hashTable[i];
            System.out.print("\nChain " + i+"\n-------------\n");
            if (list != null) {
                for (int j = 0; j < list.size(); j++) {
                    System.out.println(j+". "+list.get(j) + ", ");
                }
            }
        }
    }


}
