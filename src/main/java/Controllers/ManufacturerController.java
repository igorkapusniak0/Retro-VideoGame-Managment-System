package Controllers;

import com.example.ca2.RetroManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import storing.LinkedList;
import utils.ManufacturerUtil;

import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ManufacturerController {

    private Scene scene;
    private ManufacturerUtil chosenManufacturer;

    private ManufacturerUtil manufacturer;
    private RetroManager retroManager;
    public static LinkedList<ManufacturerUtil> manufacturerList = new LinkedList<>();

    //////////////////////////////////////////////////////////////////////////////////
    @FXML
    public TableView<ManufacturerUtil> manufacturerTableView;
    @FXML
    private TableColumn<ManufacturerUtil, String> manufacturerNameCol;
    @FXML
    private TextField manufacturerNameInput;
    @FXML TextField searchManufacturer;




    //////////////////////////////////////////////////////////////////////////////////


    public void editManufacturer(){
        chosenManufacturer.setManufacturer(manufacturerNameInput.getText());
        System.out.println(manufacturerList.display());

    }
    @FXML
    public void initialize() {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        scheduler.scheduleAtFixedRate(()->API.updateListView(searchManufacturer.getText(),manufacturerTableView,manufacturerList.head), 0, 1, TimeUnit.SECONDS);
        manufacturerNameCol.setCellValueFactory(new PropertyValueFactory<>("manufacturer"));
        manufacturerTableView.setOnMouseClicked(event -> {
            if (event.getButton().equals(MouseButton.PRIMARY) && event.getClickCount() == 2) {
                try {
                    handleTableViewPrimaryDoubleClick();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } else if (event.getButton().equals(MouseButton.SECONDARY) && event.getClickCount() == 2) {
                handleTableViewSecondaryDoubleClick();
            }
        });
    }

    public void addManufacturerButton(ActionEvent event){
        String manufacturerName = manufacturerNameInput.getText();
        manufacturer = new ManufacturerUtil(manufacturerName);

        if (manufacturerName.isEmpty()){
            System.out.println("Please fill in all fields.");
        }else{
            manufacturerList.add(manufacturer);
            manufacturerTableView.getItems().add(manufacturer);
            manufacturerNameInput.clear();
            System.out.println("Manufacturer List");
            System.out.println(manufacturerList.display());

        }
    }
    public void removeManufacturerButton(ActionEvent event){
        if (chosenManufacturer!=null){
            manufacturerList.remove(chosenManufacturer);
            System.out.println("1, "+manufacturerList.display());
        }else{
            System.out.println("No manufacturer selected");
        }
    }

    @FXML
    private void handleTableViewPrimaryDoubleClick() throws IOException {
        ManufacturerUtil selectedManufacturer = manufacturerTableView.getSelectionModel().getSelectedItem();
        if (selectedManufacturer != null) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Scenes/GameMachine.fxml"));
            Parent root = loader.load();

            GameMachine gameMachineController = loader.getController();
            gameMachineController.setManufacturer(selectedManufacturer);

            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

            // Closing the previous window if needed
            Stage currentStage = (Stage) manufacturerTableView.getScene().getWindow();
            currentStage.close();
        }
    }

    @FXML
    private void handleTableViewSecondaryDoubleClick() {
        chosenManufacturer = manufacturerTableView.getSelectionModel().getSelectedItem();
        System.out.println("chosen: "+chosenManufacturer);
    }

    public void switchToSceneDashboard(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/Scenes/Dashboard.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


}




