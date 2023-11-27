package Controllers;

import com.example.ca2.RetroManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import storing.LinkedList;
import utils.DeveloperUtil;
import utils.ManufacturerUtil;
import utils.PublisherUtil;

import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ManufacturerController {

    private Scene scene;
    private ManufacturerUtil chosenManufacturer;
    private DeveloperUtil chosenDeveloper;
    private PublisherUtil chosenPublisher;

    private ManufacturerUtil manufacturer;
    private DeveloperUtil developer;
    private PublisherUtil publisher;
    private RetroManager retroManager;
    public static LinkedList<ManufacturerUtil> manufacturerList = new LinkedList<>();
    public static LinkedList<PublisherUtil> publisherList = new LinkedList<>();
    public static LinkedList<DeveloperUtil> developerList = new LinkedList<>();

    //////////////////////////////////////////////////////////////////////////////////
    @FXML
    public TableView<ManufacturerUtil> manufacturerTableView;
    @FXML
    private TableColumn<ManufacturerUtil, String> manufacturerNameCol;
    @FXML
    private TextField manufacturerNameInput;
    @FXML TextField searchManufacturer;
    ////////////////////////////////////////////////////////////
    @FXML
    public TableView<DeveloperUtil> developerTableView;
    @FXML
    private TableColumn<DeveloperUtil, String> developerNameCol;
    @FXML
    private TextField developerNameInput;
    @FXML TextField searchDeveloper;
    ///////////////////////////////////////////////////////////////
    @FXML
    public TableView<PublisherUtil> publisherTableView;
    @FXML
    private TableColumn<PublisherUtil, String> publisherNameCol;
    @FXML
    private TextField publisherNameInput;
    @FXML TextField searchPublisher;

    //////////////////////////////////////////////////////////////////////////////////

    @FXML
    public void initialize() {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        scheduler.scheduleAtFixedRate(()->API.updateListView(searchManufacturer.getText(),manufacturerTableView,manufacturerList.head), 0, 1, TimeUnit.SECONDS);
        scheduler.scheduleAtFixedRate(()->API.updateListView(searchPublisher.getText(),publisherTableView,publisherList.head), 0, 1, TimeUnit.SECONDS);
        scheduler.scheduleAtFixedRate(()->API.updateListView(searchDeveloper.getText(),developerTableView,developerList.head), 0, 1, TimeUnit.SECONDS);

        manufacturerNameCol.setCellValueFactory(new PropertyValueFactory<>("manufacturer"));
        developerNameCol.setCellValueFactory(new PropertyValueFactory<>("developer"));
        publisherNameCol.setCellValueFactory(new PropertyValueFactory<>("publisher"));


        publisherTableView.setOnMouseClicked(mouseEvent -> {
            if (mouseEvent.getButton().equals(MouseButton.SECONDARY) && mouseEvent.getClickCount()==2){
                this.chosenPublisher=publisherTableView.getSelectionModel().getSelectedItem();
            }
        });

        developerTableView.setOnMouseClicked(mouseEvent -> {
            if (mouseEvent.getButton().equals(MouseButton.SECONDARY) && mouseEvent.getClickCount()==2){
                this.chosenDeveloper=developerTableView.getSelectionModel().getSelectedItem();
            }
        });

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
    ///////////////////////////////////////////////////////////////////////////////////////////////////
    public void editManufacturer(){
        if (chosenManufacturer!=null){
            chosenManufacturer.setManufacturer(manufacturerNameInput.getText());
            System.out.println(manufacturerList.display());
        }

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
    ///////////////////////////////////////////////////////////////////////////////////////////////////
    public void editPublisher(){
        if (chosenPublisher!=null){
            chosenPublisher.setPublisher(publisherNameInput.getText());
        }else{
            chosenPublisher.setPublisher("failed");
        }
    }
    public void addPublisher(){
        String publisherName = publisherNameInput.getText();
        publisher = new PublisherUtil(publisherName);

        if (publisherName.isBlank()){
            System.out.println("Please fill in all fields.");
        }else{
            publisherList.add(publisher);
            publisherTableView.getItems().add(publisher);
            System.out.println(publisherList.display());
        }
    }

    public void removePublisherButton(ActionEvent event){
        if (chosenPublisher!=null){
            publisherList.remove(chosenPublisher);
            System.out.println("1, "+publisherList.display());
        }else{
            System.out.println("No Publisher selected");
        }
    }
    //////////////////////////////////////////////////////////////////////////////////////////////////
    public void editDeveloper(){
        if (chosenDeveloper!=null){
            chosenDeveloper.setDeveloper(developerNameInput.getText());
        }else{
            chosenDeveloper.setDeveloper("failed");
        }
    }
    public void addDeveloper(){
        String developerName = developerNameInput.getText();
        developer = new DeveloperUtil(developerName);
        if (developerName.isBlank()){
            System.out.println("Please fill in all fields.");
        }else{
            developerList.add(developer);
            developerTableView.getItems().add(developer);
        }
    }

    public void removeDeveloperButton(ActionEvent event){
        if (chosenDeveloper!=null){
            developerList.remove(chosenDeveloper);
            System.out.println("1, "+developerList.display());
        }else{
            System.out.println("No developer selected");
        }
    }
    ///////////////////////////////////////////////////////////////////////////////


    @FXML
    private void handleTableViewPrimaryDoubleClick() throws IOException {
        ManufacturerUtil selectedManufacturer = manufacturerTableView.getSelectionModel().getSelectedItem();
        if (selectedManufacturer != null) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Scenes/GameMachine.fxml"));
            Parent root = loader.load();

            GameMachineController gameMachineController = loader.getController();
            gameMachineController.setManufacturer(selectedManufacturer);

            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

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

    public void switchToSceneMachine(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/Scenes/GameMachine.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void save(){
        API.save("data.ser");
    }
    public void load(){
        API.load("data.ser");
    }


}




