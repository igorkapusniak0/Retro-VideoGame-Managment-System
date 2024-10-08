package Controllers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import models.Game;
import models.Machine;
import models.OriginalGame;
import models.PortedGame;
import storing.LinkedList;
import storing.Node;
import utils.DeveloperUtil;
import utils.ManufacturerUtil;
import utils.PublisherUtil;

import java.io.IOException;
import java.util.Comparator;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class PortController {
    private Scene scene;
    private PortedGame chosenPort;
    private OriginalGame originalGame;
    private Machine machine;
    private GameMachineController gameMachineController;


    @FXML
    private TextField searchPort;
    @FXML
    private TableView<PortedGame> portTableView;
    @FXML
    private TableColumn<PortedGame, String> portNameCol;
    @FXML
    private TableColumn<PortedGame, String> portDescriptionCol;
    @FXML
    private TableColumn<PortedGame, String> portDeveloperCol;
    @FXML
    private TableColumn<PortedGame, String> portPublisherCol;
    @FXML
    private TableColumn<PortedGame, Integer> portYearCol;
    @FXML
    private TableColumn<PortedGame, String> portCoverCol;
    @FXML
    private TableColumn<PortedGame, String> portOriginalCol;
    @FXML
    private TableColumn<PortedGame, String> portMachineCol;

    /////////////////////////////////////////////////////////////////////
    @FXML
    private TextField portURLInput;
    @FXML
    private ComboBox<PublisherUtil> portPublisherInput;
    @FXML
    private ComboBox<DeveloperUtil> portDeveloperInput;
    @FXML
    private ComboBox<String> portReleaseYearInput;
    @FXML
    private ComboBox<Machine> portMachineInput;
    @FXML
    private Label selectedPort;
    ////////////////////////
    @FXML
    private Label nameLabel;
    @FXML
    private Label desLabel;
    @FXML
    private Label imageLabel;
    @FXML
    private Label developerLabel;
    @FXML
    private Label publisherLabel;
    @FXML
    private Label yearLabel;
    @FXML
    private Label machineName;

    public void setOriginalGame(OriginalGame originalGame){
        this.originalGame = originalGame;
        initialize();
    }



    @FXML
    public void initialize(){

        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        scheduler.scheduleAtFixedRate(()->API.updateListViewHashing(searchPort.getText(),portTableView,originalGame.portedGames), 0, 1, TimeUnit.SECONDS);


        portNameCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        portDescriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        portDeveloperCol.setCellValueFactory(new PropertyValueFactory<>("developer"));
        portPublisherCol.setCellValueFactory(new PropertyValueFactory<>("publisher"));
        portCoverCol.setCellValueFactory(new PropertyValueFactory<>("cover"));
        portYearCol.setCellValueFactory(new PropertyValueFactory<>("releaseYear"));
        portOriginalCol.setCellValueFactory(new PropertyValueFactory<>("originalGame"));
        portMachineCol.setCellValueFactory(new PropertyValueFactory<>("machine"));

        portTableView.setOnMouseClicked(e->{
            if (e.getButton().equals(MouseButton.SECONDARY)&&(e.getClickCount()==2)){
                handleTableViewSecondaryDoubleClick();
            }
        });
        updateComboBox();
        updateDeveloperComboBox();
        updatePublisherComboBox();

        API.yearOptions(portReleaseYearInput);
    }
    private boolean checkFieldStatus(){

        PublisherUtil publisher = portPublisherInput.getValue();
        Integer releaseYear = -1;
        String cover = portURLInput.getText();
        Machine machine = portMachineInput.getValue();

        boolean isValid = true;
        if (portReleaseYearInput.getValue()!=null && !portReleaseYearInput.getValue().isBlank()){
            try {
                releaseYear = Integer.parseInt(portReleaseYearInput.getValue());
                if (releaseYear < 1950 || releaseYear > 2024){
                    yearLabel.setText("Invalid Launch Year");
                    isValid= false;
                }else{
                    yearLabel.setText("");
                }
            }catch (NumberFormatException e){
                yearLabel.setText("Enter Integer");
                isValid=false;
            }
        }else{
            yearLabel.setText("Enter Integer");
            isValid=false;
        }

        if (publisher==null){
            publisherLabel.setText("Invalid Publisher");
            isValid=false;
        }else{
            publisherLabel.setText("");
        }
        if (cover.isBlank()){
            imageLabel.setText("Invalid URL");
            isValid=false;
        }else{
            imageLabel.setText("");
        }
        if (machine==null){
            machineName.setText("Invalid Machine");
            isValid = false;
        }
        return isValid;
    }
    @FXML
    private void addPort(){
        boolean isValid = checkFieldStatus();
        String title = originalGame.getTitle();
        String description = originalGame.getDescription();
        DeveloperUtil developer = originalGame.getDeveloper();
        PublisherUtil publisher = portPublisherInput.getValue();
        String releaseYear = portReleaseYearInput.getValue();
        String cover = portURLInput.getText();
        OriginalGame originalGame1 = this.originalGame;
        Machine machine = portMachineInput.getValue();

        if (isValid){
            Integer year = Integer.parseInt(releaseYear);
            PortedGame newPort = new PortedGame(title,publisher,description,developer,machine,year,cover,originalGame1);
            originalGame1.addPortedGame(newPort);
            machine.portedGames.add(newPort,newPort.getReleaseYear());
            portTableView.getItems().add(newPort);
            portURLInput.clear();
            System.out.println(this.originalGame);
            System.out.println(this.originalGame.getOriginalMachine());
            System.out.println(this.originalGame.portedGames);
            this.originalGame.getPortedGames().display();
            System.out.println(this.originalGame.portedGames.hashTable);

        }
    }
    @FXML
    private void editPort() {
        PublisherUtil publisher = portPublisherInput.getValue();
        String releaseYear = portReleaseYearInput.getValue();
        String cover = portURLInput.getText();
        Machine machine = portMachineInput.getValue();
        DeveloperUtil developer = originalGame.getDeveloper();

        if (publisher != null) {
            chosenPort.setPublisher(publisher);
        }
        if (releaseYear != null && !releaseYear.isBlank()) {
            chosenPort.setReleaseYear(Integer.parseInt(releaseYear));
        }
        if (!cover.isBlank()) {
            chosenPort.setCover(cover);
            portURLInput.clear();
        }
        if (developer != null) {
            chosenPort.setDeveloper(developer);
        }
        if (machine != null) {
            chosenPort.setMachine(machine);
        }
    }

    @FXML
    private void removePort(){
        if (chosenPort!=null){
            chosenPort.getOriginalGame().getPortedGames().remove(chosenPort,chosenPort.getReleaseYear());
            chosenPort=null;
        }else{
            System.out.println("remove failed");
        }
    }

    private void handleTableViewSecondaryDoubleClick(){
        chosenPort = portTableView.getSelectionModel().getSelectedItem();
        if (chosenPort!=null){
            selectedPort.setText("Selected Port: " + chosenPort.getTitle());
            System.out.println("try");
        }
        else{
            selectedPort.setText("Selected Port: null");
        }
    }

    private void updateComboBox(){
        if (GameMachineController.gameMachineHashing.hashTable != null){
            Platform.runLater(()->{
                portMachineInput.getItems().clear();
                for(int i = 0; i< GameMachineController.gameMachineHashing.hashTable.length; i++){
                    LinkedList<Machine> list = GameMachineController.gameMachineHashing.hashTable[i];
                    if (list!=null){
                        storing.Node<Machine> current = list.head;

                        while (current!=null){
                            if (current.data!=originalGame.getOriginalMachine()){
                                portMachineInput.getItems().add(current.data);
                            }
                            current = current.next;
                        }
                    }
                }
            });
        }
    }

    private void updatePublisherComboBox(){
        if (ManufacturerController.publisherList != null){
            Platform.runLater(()->{
                portPublisherInput.getItems().clear();
                LinkedList<PublisherUtil> list = ManufacturerController.publisherList;
                    if (list!=null){
                        storing.Node<PublisherUtil> current = list.head;
                        while (current!=null){
                            portPublisherInput.getItems().add(current.data);
                            current = current.next;
                        }
                    }
            });
        }
    }

    private void updateDeveloperComboBox(){
        if (ManufacturerController.developerList != null){
            Platform.runLater(()->{
                portDeveloperInput.getItems().clear();
                LinkedList<DeveloperUtil> list = ManufacturerController.developerList;
                if (list!=null){
                    storing.Node<DeveloperUtil> current = list.head;
                    while (current!=null){
                        portDeveloperInput.getItems().add(current.data);
                        current = current.next;
                    }
                }
            });
        }
    }

    public void switchToSceneGame(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Scenes/GameScene.fxml"));
        Parent root1 = loader.load();

        GameController gameController = loader.getController();
        gameController.setMachine(this.originalGame.getOriginalMachine());

        Stage stage1 = new Stage();
        Scene scene = new Scene(root1);
        stage1.setScene(scene);
        stage1.show();
        Stage currentStage = (Stage) portTableView.getScene().getWindow();
        currentStage.close();
    }
    public void save(){
        API.save("data.ser");
    }
    public void load(){
        API.load("data.ser");
    }
    public void clear(){
        API.clear("data.ser");
        portTableView.getItems().clear();
    }
    public void sortByName(){
        for (int i = 0; i < 8; i++) {
            if (this.originalGame.portedGames.hashTable[i].head != null) {
                Comparator<PortedGame> integerComparator = Comparator.comparing(Game::getTitle);
                this.originalGame.portedGames.hashTable[i].sort(integerComparator);
                portTableView.getItems().clear();
                this.originalGame.portedGames.display();
            }
        }
    }
    public void sortByReleaseYear(){
        for (int i = 0; i < 8; i++) {
            if (this.originalGame.portedGames.hashTable[i].head != null) {
                Comparator<PortedGame> integerComparator = Comparator.comparing(PortedGame::getReleaseYear);
                this.originalGame.portedGames.hashTable[i].sort(integerComparator);
                portTableView.getItems().clear();
                this.originalGame.portedGames.display();
            }
        }
    }

}
