package Controllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Machine;
import models.OriginalGame;
import models.PortedGame;
import storing.LinkedList;
import storing.Node;
import utils.DeveloperUtil;
import utils.ManufacturerUtil;
import utils.PublisherUtil;

public class PortController {
    private Scene scene;
    private PortedGame chosenPort;
    private OriginalGame originalGame;

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
        portNameCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        portDescriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        portDeveloperCol.setCellValueFactory(new PropertyValueFactory<>("developer"));
        portPublisherCol.setCellValueFactory(new PropertyValueFactory<>("publisher"));
        portCoverCol.setCellValueFactory(new PropertyValueFactory<>("cover"));
        portYearCol.setCellValueFactory(new PropertyValueFactory<>("releaseYear"));
        portOriginalCol.setCellValueFactory(new PropertyValueFactory<>("originalGame"));
        portMachineCol.setCellValueFactory(new PropertyValueFactory<>("machine"));


        handleTableViewSecondaryDoubleClick();
        updateComboBox();
        updateDeveloperComboBox();
        updatePublisherComboBox();

        API.yearOptions(portReleaseYearInput);
    }
    @FXML
    private void addPort(){
        String title = originalGame.getTitle();
        String description = originalGame.getDescription();
        DeveloperUtil developer = originalGame.getDeveloper();
        PublisherUtil publisher = portPublisherInput.getValue();
        Integer releaseYear = Integer.valueOf(portReleaseYearInput.getValue());
        String cover = portURLInput.getText();
        OriginalGame originalGame1 = this.originalGame;
        Machine machine = portMachineInput.getValue();

        PortedGame newPort = new PortedGame(title,publisher,description,developer,machine,releaseYear,cover,originalGame1);
        machine.addPortedGame(newPort);
    }
    @FXML
    private void editPort(){
        chosenPort.setPublisher(portPublisherInput.getValue());
        chosenPort.setReleaseYear(Integer.parseInt(portReleaseYearInput.getValue()));
        chosenPort.setCover(portURLInput.getText());
        chosenPort.setDeveloper(portDeveloperInput.getValue());
    }
    @FXML
    private void removePort(){
        if (chosenPort!=null){
            chosenPort.getMachine().removePortedGame(chosenPort);
            chosenPort=null;
        }else{
            System.out.println("remove failed");
        }
    }

    private void handleTableViewSecondaryDoubleClick(){
        chosenPort = portTableView.getSelectionModel().getSelectedItem();
        if (chosenPort!=null){
            selectedPort.setText("Selected Port: " + chosenPort.getTitle());
        }
        else{
            selectedPort.setText("Selected Port: null");
        }
    }

    private void updateComboBox(){
        if (GameMachineController.gameMachineHashing.hashTable != null){
            Platform.runLater(()->{
                portMachineInput.getItems().clear();
                for(int i =0;i<GameMachineController.gameMachineHashing.hashTable.length;i++){
                    LinkedList<Machine> list = GameMachineController.gameMachineHashing.hashTable[i];
                    if (list!=null){
                        storing.Node<Machine> current = list.head;

                        while (current!=null){
                            portMachineInput.getItems().add(current.data);
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

}
