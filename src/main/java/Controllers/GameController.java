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
    }

    @FXML
    public void initialize() {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        scheduler.scheduleAtFixedRate(()->API.updateListViewHashing(searchGame.getText(),gameTableView,machine.getGames()), 0, 1, TimeUnit.SECONDS);

        gameNameCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        gameDescriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        gameDeveloperCol.setCellValueFactory(new PropertyValueFactory<>("developer"));
        gamePublisherCol.setCellValueFactory(new PropertyValueFactory<>("publisher"));
        gameYearCol.setCellValueFactory(new PropertyValueFactory<>("releaseYear"));
        gameImageCol.setCellValueFactory(new PropertyValueFactory<>("cover"));

        updateComboBox();

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
            else{
            }
        });
        if (this.machine!=null){
            machineName.setText(this.machine.getName());
        }else{
            machineName.setText("Failed still null");
        }
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
            this.machine.getGames().display();
            gameNameInput.clear();
            gameDescriptionInput.clear();
            gameUrlInput.clear();
        }
    }
    public void removeGameButton(){
        if (chosenGame!=null){
            this.machine.getGames().remove(chosenGame);
            chooseGame.setText("");

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
        chooseGame.setText("Selected Machine: "+chosenGame.getTitle());
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
