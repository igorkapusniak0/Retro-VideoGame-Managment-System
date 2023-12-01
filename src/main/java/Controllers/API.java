package Controllers;


import javafx.application.Platform;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import models.Machine;
import storing.Hashing;
import storing.LinkedList;
import storing.Node;
import utils.DeveloperUtil;
import utils.ManufacturerUtil;
import utils.PublisherUtil;

import java.io.*;
import java.util.List;


public class API {
    public Hashing hashing;

    public static <T> void updateListView(String filter, TableView tableView, Node<T> head) {
        Platform.runLater(() -> {
            tableView.getItems().clear();
            tableView.getItems().removeIf(item -> !item.toString().contains(filter));
            Node<T> current = head;
            while (current != null){
                if (current.data.toString().contains(filter)) {
                    if (!tableView.getItems().contains(current.data)) {
                        tableView.getItems().add(current.data);
                    }
                }
                current = current.next;
            }
        });
    }

    public static <T extends Comparable<T>> void updateListViewHashing(String filter, TableView tableView, Hashing hashing) {
        Platform.runLater(() -> {
            if (tableView!=null && hashing.hashTable!=null) {
                tableView.getItems().clear();
                tableView.getItems().removeIf(item -> !item.toString().contains(filter));
                for (int i = 0; i < hashing.hashTable.length; i++) {
                    LinkedList<T> list = hashing.hashTable[i];
                    if (list != null) {
                        Node<T> current = list.head;
                        while (current != null) {
                            if (current.data.toString().contains(filter)) {
                                if (!tableView.getItems().contains(current.data)) {
                                    tableView.getItems().add(current.data);
                                }
                            }
                            current = current.next;
                        }
                    }
                }
            }});
    }



    public static void yearOptions(ComboBox comboBox){
        int[] years = new int[75];
        for (int i=0;i<=74;i+=1){
            years[i]=1950+i;
        }
        for (int year : years) {
            comboBox.getItems().add(String.valueOf(year));
        }
    }

    public static void updateComboBox(LinkedList list, ComboBox comboBox){
        if (list != null){
            Platform.runLater(()->{
                comboBox.getItems().clear();
                if (list!=null){
                    storing.Node<Object> current = list.head;
                    while (current!=null){
                        comboBox.getItems().add(current.data);
                        current = current.next;
                    }
                }
            });
        }
    }

    public static void save(String fileName) {
        try (FileOutputStream fileOutputStream = new FileOutputStream(fileName);
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
            objectOutputStream.writeObject(ManufacturerController.manufacturerList);
            objectOutputStream.writeObject(ManufacturerController.publisherList);
            objectOutputStream.writeObject(ManufacturerController.developerList);
            objectOutputStream.writeObject(GameMachineController.gameMachineHashing);
            System.out.println("Save success");
        } catch (IOException ignored) {
            System.out.println("Save Error");
        }
    }
    /**
     * Clears the contents of a file by writing a null object.
     *
     * @param fileName the name of the file to be cleared
     */
    public static void clear(String fileName) {
        try (FileOutputStream fileOutputStream = new FileOutputStream(fileName);
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
            objectOutputStream.writeObject(null);
            ManufacturerController.developerList.head=null;
            ManufacturerController.manufacturerList.head=null;
            ManufacturerController.publisherList.head=null;
            GameMachineController.gameMachineHashing.clearHashTable();
        } catch (IOException ignored) {
        }
    }


    /**
     * Loads data from a file into the corresponding lists and hash table.
     *
     * @param fileName the name of the file to load data from
     */
    public static void load(String fileName) {
        try (FileInputStream fileInputStream = new FileInputStream(fileName);
             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
            ManufacturerController.manufacturerList = (LinkedList<ManufacturerUtil>) objectInputStream.readObject();
            ManufacturerController.publisherList = (LinkedList<PublisherUtil>) objectInputStream.readObject();
            ManufacturerController.developerList = (LinkedList<DeveloperUtil>) objectInputStream.readObject();
            GameMachineController.gameMachineHashing = (Hashing<Machine>) objectInputStream.readObject();

        } catch (IOException | ClassNotFoundException ignored) {}
    }


    /*public static void quickSort(int[] a,int lowerIndex, int higherIndex) {
        int leftIndex = lowerIndex;
        int rightIndex = higherIndex;

        int pivot = a[lowerIndex + (higherIndex - leftIndex) / 2];

        while (leftIndex <= rightIndex) {
            while (a[lowerIndex] < pivot) {
                leftIndex++;
            }
            while (a[rightIndex] > pivot) {
                rightIndex--;
            }

            if (leftIndex <= rightIndex) {
                int swap = a[leftIndex];
                a[leftIndex] = a[rightIndex];
                a[rightIndex] = swap;
                leftIndex++;
                rightIndex--;
            }
        }
        if (lowerIndex < rightIndex) {
            quickSort(a, lowerIndex, rightIndex);
        }
        if (leftIndex < higherIndex) {
            quickSort(a, lowerIndex, higherIndex);
        }
    }*/




}
