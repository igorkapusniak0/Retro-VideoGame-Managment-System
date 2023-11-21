package storing;

import java.io.Serializable;
import java.util.Scanner;

public class Hashing<T> implements Serializable {
    public LinkedList<T>[] hashTable;

    public Hashing(int size){
        hashTable = (LinkedList<T>[]) new LinkedList [size];
        for (int i=0;i<size;i++){
            hashTable[i]=new LinkedList<>();
        }
    }
    public int hashFunction(int key){
        return key%hashTable.length;
    }
    public int add(int key,T data){
        int home=hashFunction(key);
        hashTable[home].add(data);
        return home;
    }
    //TODO
    //Change to voids after testing
    public int add(T data){
        int home = hashFunction(Math.abs(data.hashCode()));
        hashTable[home].add(data);
        return home;
    }
    public int remove(T data){
        int home = hashFunction(Math.abs(data.hashCode()));
        hashTable[home].remove(data);
        return home;
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

    public static void main(String[] args){
        Hashing<String> h = new Hashing<>(5);
        Scanner k = new Scanner(System.in);

        String name;

        do{
            System.out.print("Enter name: ");
            name=k.nextLine();

            if(name.length()!=0) {
                int loc=h.add(name);
                System.out.println(name+" stored at location "+loc);
            }
        }while(name.length()!=0);
        h.display();
    }
}
