package Controllers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
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
import storing.Hashing;
import storing.LinkedList;
import utils.ManufacturerUtil;

import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class GameController {
    private Scene scene;
    private OriginalGame chosenGame;
    private Machine machine;
    private GameMachineController gameMachineController;
    //////////////////////////////////////////////////////////////////////////
    @FXML
    private TableView<OriginalGame> gameTableView;
    @FXML
    private TableColumn<OriginalGame, String> gameNameCol;
    @FXML
    private TableColumn<OriginalGame, String> gameDescriptionCol;
    @FXML
    private TableColumn<OriginalGame, String> gameDeveloperCol;
    @FXML
    private TableColumn<OriginalGame, String> gamePublisherCol;
    @FXML
    private TableColumn<OriginalGame, Integer> gameYearCol;
    @FXML
    private TableColumn<OriginalGame, String> gameImageCol;
    /////////////////////////////////////////////////////////////
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
    ////////////////////////////////////////////////////////////////////////////
    @FXML
    private TextField gameNameInput;
    @FXML
    private TextField gameDescriptionInput;
    @FXML
    private TextField gameUrlInput;
    @FXML
    private TextField searchGame;
    /////////////////////////////////////////////////////////////////////////
    @FXML
    private ComboBox<String> comboDeveloper;
    @FXML
    private ComboBox<String> comboPublisher;
    @FXML
    private ComboBox<String> comboLaunchYear;
    @FXML
    private ComboBox<Machine> comboMachine;
    //////////////////////////////////////////////////////////////////////////
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
    private Label chooseGame;
    @FXML
    private MenuButton menuButton;

    //////////////////////////////////////////////////////////////////////////
    public void setMachine(Machine machine){
        this.machine=machine;
        initialize();
    }

    @FXML
    public void initialize() {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        scheduler.scheduleAtFixedRate(()->API.updateListViewHashing("",gameTableView,machine.originalGames), 0, 1, TimeUnit.SECONDS);
        if (this.machine!=null){
            machineName.setText(this.machine.getName());
        }else{
            machineName.setText("Machine Null");
        }


        gameNameCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        gameDescriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        gameDeveloperCol.setCellValueFactory(new PropertyValueFactory<>("developer"));
        gamePublisherCol.setCellValueFactory(new PropertyValueFactory<>("publisher"));
        gameYearCol.setCellValueFactory(new PropertyValueFactory<>("releaseYear"));
        gameImageCol.setCellValueFactory(new PropertyValueFactory<>("cover"));

        portNameCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        portDescriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        portDeveloperCol.setCellValueFactory(new PropertyValueFactory<>("developer"));
        portPublisherCol.setCellValueFactory(new PropertyValueFactory<>("publisher"));
        portCoverCol.setCellValueFactory(new PropertyValueFactory<>("cover"));
        portYearCol.setCellValueFactory(new PropertyValueFactory<>("releaseYear"));
        portOriginalCol.setCellValueFactory(new PropertyValueFactory<>("originalGame"));

        updateComboBox();

        comboPublisher.getItems().add("1");
        comboDeveloper.getItems().add("q");

        int[] years = new int[75];
        for (int i=0;i<=74;i+=1){
            years[i]=1950+i;
        }
        for (int year : years) {
            comboLaunchYear.getItems().add(String.valueOf(year));
        }

        gameTableView.setOnMouseClicked(event -> {
            if (event.getButton().equals(MouseButton.SECONDARY) && event.getClickCount()==2){
                handleTableViewSecondaryDoubleClick();
            }
            else if (event.getButton().equals(MouseButton.PRIMARY) && event.getClickCount()==2){
                System.out.println("dododo");
            }
        });

    }

    public boolean checkFieldStatus(){
        String gameTitle = gameNameInput.getText();
        String gameDescription = gameDescriptionInput.getText();
        String gameDeveloper = comboDeveloper.getValue();
        String gameCover = gameUrlInput.getText();
        String gamePublisher = comboPublisher.getValue();
        Integer gameLaunchYear = -1;

        boolean isValid = true;

        if (comboLaunchYear.getValue() != null && !comboLaunchYear.getValue().isBlank()) {
            try {
                gameLaunchYear = Integer.parseInt(comboLaunchYear.getValue());
                if (gameLaunchYear < 1950 || gameLaunchYear > 2024) {
                    yearLabel.setText("Invalid Launch Year");
                    isValid = false;
                } else {
                    yearLabel.setText("");
                }
            } catch (NumberFormatException e) {
                yearLabel.setText("Enter Integer");
                isValid = false;
            }
        } else {
            yearLabel.setText("Enter Integer");
            isValid = false;
        }

        if (gameTitle.isBlank()) {
            nameLabel.setText("Invalid Title");
            isValid = false;
        } else {
            nameLabel.setText("");
        }

        if (gameDescription.isBlank()) {
            desLabel.setText("Invalid Description");
            isValid = false;
        } else {
            desLabel.setText("");
        }

        if (gameCover.isBlank()) {
            imageLabel.setText("Invalid URL");
            isValid = false;
        } else {
            imageLabel.setText("");
        }

        if (gamePublisher == null || gamePublisher.isBlank()) {
            publisherLabel.setText("Invalid Publisher");
            isValid = false;
        } else {
            publisherLabel.setText("");
        }

        if (gameDeveloper == null || gameDeveloper.isBlank()) {
            developerLabel.setText("Invalid Developer");
            isValid = false;
        } else {
            developerLabel.setText("");
        }
        return isValid;
    }
    public void addGameButton() {
        boolean isValid = checkFieldStatus();
        String gameTitle = gameNameInput.getText();
        String gameDescription = gameDescriptionInput.getText();
        String gameDeveloper = comboDeveloper.getValue();
        String gameCover = gameUrlInput.getText();
        String gamePublisher = comboPublisher.getValue();
        String gameLaunchYear = comboLaunchYear.getValue();

        if (isValid) {
            Integer launchYear = Integer.parseInt(gameLaunchYear);
            OriginalGame originalGame = new OriginalGame(gameTitle,gamePublisher ,gameDescription, gameDeveloper,this.machine, launchYear, gameCover);
            this.machine.getGames().add(originalGame);
            gameTableView.getItems().add(originalGame);
            System.out.println(originalGame + " is added");
            machine.getGames().display();
            this.machine.originalGames.display();
            gameNameInput.clear();
            gameDescriptionInput.clear();
            gameUrlInput.clear();
            gameTableView.refresh();
        }
    }
    public void removeGameButton(){
        System.out.println(this.machine);
        if (chosenGame!=null){
            this.machine.getGames().remove(chosenGame);
            chosenGame=null;
            chooseGame.setText("");
            gameTableView.refresh();
            machine.originalGames.display();
        }
    }

    public void editGameButton(){
        boolean isValid = checkFieldStatus();
        if (chosenGame!=null){
            if (isValid){
                String gameTitle = gameNameInput.getText();
                String gameDescription = gameDescriptionInput.getText();
                String gameDeveloper = comboDeveloper.getValue();
                String gameCover = gameUrlInput.getText();
                String gamePublisher = comboPublisher.getValue();
                Integer gameLaunchYear = Integer.valueOf(comboLaunchYear.getValue());

                chosenGame.setTitle(gameTitle);
                chosenGame.setDescription(gameDescription);
                chosenGame.setDeveloper(gameDeveloper);
                chosenGame.setCover(gameCover);
                chosenGame.setPublisher(gamePublisher);
                chosenGame.setReleaseYear(gameLaunchYear);

                gameNameInput.clear();
                gameDescriptionInput.clear();
                gameUrlInput.clear();
                chosenGame=null;
                chooseGame.setText("");
                gameTableView.refresh();
            }

        }
    }
    public void switchToSceneMachine(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/Scenes/GameMachine.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    private void handleTableViewSecondaryDoubleClick() {
        chosenGame = gameTableView.getSelectionModel().getSelectedItem();
        if (chosenGame != null){
            chooseGame.setText("Selected Machine: "+chosenGame.getTitle());
        }else{
            chooseGame.setText("Selected Machine: null");
        }
        System.out.println(chosenGame);
    }

    private void updateComboBox(){
        if (GameMachineController.gameMachineHashing.hashTable != null){
            Platform.runLater(()->{
               comboMachine.getItems().clear();
                for(int i =0;i<GameMachineController.gameMachineHashing.hashTable.length;i++){
                    LinkedList<Machine> list = GameMachineController.gameMachineHashing.hashTable[i];
                    if (list!=null){
                        storing.Node<Machine> current = list.head;

                        while (current!=null){
                            comboMachine.getItems().add(current.data);
                            current = current.next;
                        }
                    }
                }
            });
        }
    }


}
