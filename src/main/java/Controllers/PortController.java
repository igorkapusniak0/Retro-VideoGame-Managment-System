package Controllers;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import models.PortedGame;

public class PortController {
    private Scene scene;

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
    }

}
