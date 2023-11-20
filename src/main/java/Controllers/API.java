package Controllers;


import javafx.application.Platform;
import javafx.scene.control.TableView;
import storing.Hashing;
import storing.Node;

public class API {

    public static <T> void updateListView(String filter, TableView tableView, Node<T> head) {
        Platform.runLater(() -> {
            tableView.getItems().clear();
            tableView.getItems().removeIf(container1 -> !container1.toString().contains(filter));
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
}
