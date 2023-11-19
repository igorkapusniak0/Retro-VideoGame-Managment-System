package Controllers;

import com.example.ca2.RetroManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import storing.LinkedList;

import java.io.IOException;

public class ManufacturerController {
    private String chosenManufacturer;
    private RetroManager retroManager;
    public static LinkedList<String> manufacturerList = new LinkedList<>();

    //////////////////////////////////////////////////////////////////////////////////
    @FXML
    public TableView<String> manufacturerTableView;
    @FXML
    private TableColumn<String, String> manufacturerNameCol;
    @FXML
    private TextField manufacturerNameInput;

    @FXML
    private Button addManufacturer;

    //////////////////////////////////////////////////////////////////////////////////

    public void addManufacturer(String manufacturer){
        manufacturerList.add(manufacturer);
    }
    public void removeManufacturer(String manufacturer){
        manufacturerList.remove(manufacturer);
    }
    @FXML
    public void initialize() {
        manufacturerTableView.setOnMouseClicked(event -> {
            if (event.getButton().equals(MouseButton.PRIMARY) && event.getClickCount() == 2) {
                handleTableViewPrimaryDoubleClick();
            } else if (event.getButton().equals(MouseButton.SECONDARY) && event.getClickCount() == 2) {
                handleTableViewSecondaryDoubleClick();
            }
        });

    }

    public void addManufacturerButton(ActionEvent event){
        String manufacturerName = manufacturerNameInput.getText();
        if (manufacturerName.isEmpty()){
            System.out.println("Please fill in all fields.");
        }else{
            addManufacturer(manufacturerName);
            manufacturerTableView.getItems().add(manufacturerName);
            manufacturerNameInput.clear();

        }

    }

    @FXML
    private void handleTableViewPrimaryDoubleClick() {
        String selectedManufacturer = manufacturerTableView.getSelectionModel().getSelectedItem();
        if (selectedManufacturer != null) {
            try {
                // Load the new scene
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/Scenes/GameMachine"));
                Parent root2 = loader.load();

                // Pass the selectedPort to the controller of the new scene if needed
                GameMachine gameMachineController = loader.getController();
                gameMachineController.setManufacturer(selectedManufacturer);

                // Switch to the new scene
                Stage stage = (Stage) manufacturerTableView.getScene().getWindow();
                Scene scene = new Scene(root2);
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace(); // Handle the exception appropriately
            }
        }
    }

    @FXML
    private void handleTableViewSecondaryDoubleClick() {
        chosenManufacturer = manufacturerTableView.getSelectionModel().getSelectedItem();
        }
    }


