package Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseButton;
import models.Machine;

public class GameMachine {

    private Machine chosenGameMachine;
    @FXML
    private TableView<Machine> machineTableView;

    @FXML
    public void initialize() {
        machineTableView.setOnMouseClicked(mouseEvent -> {
            if (mouseEvent.getClickCount() == 2 && mouseEvent.getButton() == MouseButton.SECONDARY) {
                chosenGameMachine = machineTableView.getSelectionModel().getSelectedItem();
                // Do something with the chosenGameMachine if needed
            }
        });
    }

}
