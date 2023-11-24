package Controllers;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Machine;
import models.OriginalGame;
import models.PortedGame;

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
    private ComboBox<String> portPublisherInput;
    @FXML
    private ComboBox<String> portDeveloperInput;
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

    }
    @FXML
    private addPort(){
        String title = originalGame.getTitle();
        String description = originalGame.getDescription();
        String developer = originalGame.getDeveloper();
        String publisher = portPublisherInput.getValue();
        Integer releaseYear = Integer.valueOf(portReleaseYearInput.getValue());
        String cover = portURLInput.getText();
        OriginalGame originalGame1 = this.originalGame;
        Machine machine = inp
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

}
