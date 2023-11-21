package Controllers;

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
import models.Machine;
import storing.Hashing;
import storing.LinkedList;
import utils.ManufacturerUtil;


import java.io.IOException;
import java.net.URL;
import java.util.Collection;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class GameMachineController {

    private Scene scene;

    private Machine chosenGameMachine;
    private ManufacturerUtil manufacturer;





    public static Hashing<Machine> gameMachineHashing = new Hashing<>(50);
    //////////////////////////////////////////////////////////////////////////
    @FXML
    private TableView<Machine> machineTableView;
    @FXML
    private TableColumn<API, String> gameMachineNameCol;
    @FXML
    private TableColumn<ManufacturerUtil, String> manufacturerNameCol;
    @FXML
    private TableColumn<Machine, String> gameMachineDescriptionCol;
    @FXML
    private TableColumn<Machine, String> gameMachineTypeCol;
    @FXML
    private TableColumn<Machine, String> gameMachineMediaCol;
    @FXML
    private TableColumn<Machine, Integer> gameMachineYearCol;
    @FXML
    private TableColumn<Machine, Double> gameMachinePriceCol;
    @FXML
    private TableColumn<Machine, String> gameMachineImageCol;
    ////////////////////////////////////////////////////////////////////////////
    @FXML
    private TextField gameMachineNameInput;
    @FXML
    private TextField gameMachineDescriptionInput;
    @FXML
    private TextField gameMachinePriceInput;
    @FXML
    private TextField gameMachineUrlInput;
    @FXML
    private TextField searchGameMachine;
    /////////////////////////////////////////////////////////////////////////
    @FXML
    private ComboBox<String> comboType;
    @FXML
    private ComboBox<String> comboMedia;
    @FXML
    private ComboBox<String> comboLaunchYear;
    //////////////////////////////////////////////////////////////////////////
    @FXML
    private Label macNameLabel;
    @FXML
    private Label macDesLabel;
    @FXML
    private Label macImageLabel;
    @FXML
    private Label macPriceLabel;
    @FXML
    private Label macTypeLabel;
    @FXML
    private Label macMediaLabel;
    @FXML
    private Label macYearLabel;
    @FXML
    private Label chooseMachine;

    //////////////////////////////////////////////////////////////////////////

    public void setManufacturer(ManufacturerUtil manufacturer){
        this.manufacturer=manufacturer;
    }

    @FXML
    public void initialize() {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        scheduler.scheduleAtFixedRate(()->API.updateListViewHashing(searchGameMachine.getText(),machineTableView,gameMachineHashing), 0, 1, TimeUnit.SECONDS);

        gameMachineNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        manufacturerNameCol.setCellValueFactory(new PropertyValueFactory<>("manufacturer"));
        gameMachineDescriptionCol.setCellValueFactory(new PropertyValueFactory<>("description"));
        gameMachineTypeCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        gameMachineMediaCol.setCellValueFactory(new PropertyValueFactory<>("media"));
        gameMachineYearCol.setCellValueFactory(new PropertyValueFactory<>("launchYear"));
        gameMachinePriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        gameMachineImageCol.setCellValueFactory(new PropertyValueFactory<>("image"));

        machineTableView.setOnMouseClicked(mouseEvent -> {
            if (mouseEvent.getClickCount() == 2 && mouseEvent.getButton() == MouseButton.SECONDARY) {
                chosenGameMachine = machineTableView.getSelectionModel().getSelectedItem();
            }
        });
        comboType.getItems().addAll("Game Console","Computer");
        comboMedia.getItems().addAll("Cartridge","Tape","CD","Floppy Disk");

        int[] years = new int[75];
        for (int i=0;i<=74;i+=1){
            years[i]=1950+i;
        }
        for (int year : years) {
            comboLaunchYear.getItems().add(String.valueOf(year));
        }

        machineTableView.setOnMouseClicked(event -> {
            if (event.getButton().equals(MouseButton.SECONDARY) && event.getClickCount()==2){
                handleTableViewSecondaryDoubleClick();
            }
            else{
            }
        });
    }
    public boolean checkFieldStatus(){
        String machineName = gameMachineNameInput.getText();
        String machineDescription = gameMachineDescriptionInput.getText();
        String machinePrice = gameMachinePriceInput.getText();
        String machineURL = gameMachineUrlInput.getText();
        String machineType = comboType.getValue();
        String machineMedia = comboMedia.getValue();
        Integer machineLaunchYear = -1;

        boolean isValid = true;

        if (comboLaunchYear.getValue() != null && !comboLaunchYear.getValue().isBlank()) {
            try {
                machineLaunchYear = Integer.parseInt(comboLaunchYear.getValue());
                if (machineLaunchYear < 1950 || machineLaunchYear > 2024) {
                    macYearLabel.setText("Invalid Launch Year");
                    isValid = false;
                } else {
                    macYearLabel.setText("");
                }
            } catch (NumberFormatException e) {
                macYearLabel.setText("Enter Integer");
                isValid = false;
            }
        } else {
            macYearLabel.setText("Enter Integer");
            isValid = false;
        }

        if (machineName.isBlank()) {
            macNameLabel.setText("Invalid Name");
            isValid = false;
        } else {
            macNameLabel.setText("");
        }

        if (machineDescription.isBlank()) {
            macDesLabel.setText("Invalid Description");
            isValid = false;
        } else {
            macDesLabel.setText("");
        }

        if (machinePrice.isBlank()) {
            macPriceLabel.setText("Invalid Price");
            isValid = false;
        } else {
            try {
                Double price = Double.parseDouble(machinePrice);
                if (price < 0 || price > Double.MAX_VALUE) {
                    macPriceLabel.setText("Invalid Price");
                    isValid = false;
                } else {
                    macPriceLabel.setText("");
                }
            } catch (NumberFormatException e) {
                macPriceLabel.setText("Enter Number");
                isValid = false;
            }
        }

        if (machineURL.isBlank()) {
            macImageLabel.setText("Invalid URL");
            isValid = false;
        } else {
            macImageLabel.setText("");
        }

        if (machineType == null || machineType.isBlank()) {
            macTypeLabel.setText("Invalid Type");
            isValid = false;
        } else {
            macTypeLabel.setText("");
        }

        if (machineMedia == null || machineMedia.isBlank()) {
            macMediaLabel.setText("Invalid Media");
            isValid = false;
        } else {
            macMediaLabel.setText("");
        }
        return isValid;
    }

    public void addGameMachineButton() {
        boolean isValid = checkFieldStatus();
        String machineName = gameMachineNameInput.getText();
        String machineDescription = gameMachineDescriptionInput.getText();
        String machinePrice = gameMachinePriceInput.getText();
        String machineURL = gameMachineUrlInput.getText();
        String machineType = comboType.getValue();
        String machineMedia = comboMedia.getValue();
        Integer machineLaunchYear = -1;

        if (isValid) {
            Double price = Double.parseDouble(machinePrice);
            Machine machine = new Machine(machineName, manufacturer, machineDescription, machineType, machineMedia, machineLaunchYear, price, machineURL);
            gameMachineHashing.add(machine);
            machineTableView.getItems().add(machine);
            System.out.println(machine + " is added");
            gameMachineHashing.display();
            gameMachineNameInput.clear();
            gameMachineDescriptionInput.clear();
            gameMachinePriceInput.clear();
            gameMachineUrlInput.clear();
        }
    }

    public void removeGameMachineButton(){
        if (chosenGameMachine!=null){
            gameMachineHashing.remove(chosenGameMachine);
            chooseMachine.setText("");
            machineTableView.refresh();
        }
    }
    public void editGameMachineButton(){
        boolean isValid = checkFieldStatus();
        if (chosenGameMachine!=null){
            if (isValid){
                String machineName = gameMachineNameInput.getText();
                String machineDescription = gameMachineDescriptionInput.getText();
                Double machinePrice = Double.valueOf(gameMachinePriceInput.getText());
                String machineURL = gameMachineUrlInput.getText();
                String machineType = comboType.getValue();
                String machineMedia = comboMedia.getValue();
                Integer machineLaunchYear = Integer.valueOf(comboLaunchYear.getValue());

                chosenGameMachine.setName(machineName);
                chosenGameMachine.setDescription(machineDescription);
                chosenGameMachine.setPrice(machinePrice);
                chosenGameMachine.setImage(machineURL);
                chosenGameMachine.setType(machineType);
                chosenGameMachine.setMedia(machineMedia);
                chosenGameMachine.setLaunchYear(machineLaunchYear);

                gameMachineNameInput.clear();
                gameMachineDescriptionInput.clear();
                gameMachinePriceInput.clear();
                gameMachineUrlInput.clear();
            }

        }
    }

    public void switchToSceneManufacturer(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/Scenes/ManufacturerScene.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void handleTableViewSecondaryDoubleClick() {
        chosenGameMachine = machineTableView.getSelectionModel().getSelectedItem();
        chooseMachine.setText("Selected Machine: "+chosenGameMachine.getName());
    }


}


