package Controllers;


import javafx.application.Platform;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import storing.Hashing;
import storing.LinkedList;
import storing.Node;

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

    public static <T> void updateListViewHashing(String filter, TableView tableView, Hashing hashing) {
        Platform.runLater(() -> {
            tableView.getItems().clear();
            tableView.getItems().removeIf(item -> !item.toString().contains(filter));
            for(int i =0;i<hashing.hashTable.length;i++){
                LinkedList<T> list = hashing.hashTable[i];
                if (list!=null){
                    Node<T> current = list.head;
                    while (current!=null){
                        if (current.data.toString().contains(filter)) {
                            if (!tableView.getItems().contains(current.data)) {
                                tableView.getItems().add(current.data);
                            }
                        }
                        current = current.next;
                    }
                }
            }
        });
    }

    public static <T> String search(String filter, Hashing hashing) {
        String items = "";
        for(int i =0;i<hashing.hashTable.length;i++){
            LinkedList<T> list = hashing.hashTable[i];
            if (list!=null){
                Node<T> current = list.head;
                while (current!=null){
                    if (current.data.toString().contains(filter)) {
                        items += current.data + "\n";
                    }
                    current = current.next;
                }
            }
        }
        return items;
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
}
