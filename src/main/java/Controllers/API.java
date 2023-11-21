package Controllers;


import javafx.application.Platform;
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
}
