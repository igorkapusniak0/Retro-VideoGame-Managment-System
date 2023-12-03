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
import utils.ManufacturerUtil;
import java.io.IOException;
import java.util.Comparator;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class GameMachineController {

    private Scene scene;

    private Machine chosenGameMachine;
    private ManufacturerUtil manufacturer;
    private int hashTableSize = 8;

    public static Hashing<Machine> gameMachineHashing = new Hashing<>(8);
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
    @FXML
    private ComboBox<ManufacturerUtil> comboManufacturer;
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

        comboType.getItems().addAll("Game Console","Computer");
        comboMedia.getItems().addAll("Cartridge","Tape","CD","Floppy Disk");
        API.updateComboBox(ManufacturerController.manufacturerList,comboManufacturer);

        int[] years = new int[75];
        for (int i=0;i<=74;i+=1){
            years[i]=1950+i;
        }
        for (int year : years) {
            comboLaunchYear.getItems().add(String.valueOf(year));
        }

        machineTableView.setOnMouseClicked(event -> {
            if (event.getButton().equals(MouseButton.PRIMARY) && event.getClickCount()==2){
                try {
                    handleTableViewPrimaryDoubleClick();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            else if (event.getButton().equals(MouseButton.SECONDARY) && event.getClickCount()==2){
                handleTableViewSecondaryDoubleClick();
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
        String machineLaunchYear = comboLaunchYear.getValue();
        ManufacturerUtil manufacturer = comboManufacturer.getValue();

        if (isValid) {
            Double price = Double.parseDouble(machinePrice);
            Integer launchYear = Integer.parseInt(machineLaunchYear);
            Machine machine = new Machine(machineName, manufacturer, machineDescription, machineType, machineMedia, launchYear, price, machineURL,new Hashing(hashTableSize),new Hashing(hashTableSize));
            gameMachineHashing.add(machine,machine.getLaunchYear());
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
            gameMachineHashing.remove(chosenGameMachine,chosenGameMachine.getLaunchYear());
            chosenGameMachine = null;
            chooseMachine.setText("");
        }else{
            System.out.println("Nothing selected");
        }
    }
    public void editGameMachineButton() {
        String machineName = gameMachineNameInput.getText().trim();
        String machineDescription = gameMachineDescriptionInput.getText().trim();
        String machinePriceText = gameMachinePriceInput.getText().trim();
        String machineURL = gameMachineUrlInput.getText().trim();
        String machineType = comboType.getValue();
        String machineMedia = comboMedia.getValue();
        String machineLaunchYearText = comboLaunchYear.getValue();
        ManufacturerUtil manufacturer = comboManufacturer.getValue();

        boolean isValid = true;

        if (!machineName.isBlank()) {
            chosenGameMachine.setName(machineName);
            macNameLabel.setText("");
        } else {
            macNameLabel.setText("Invalid Name");
            isValid = false;
        }

        if (!machineDescription.isBlank()) {
            chosenGameMachine.setDescription(machineDescription);
            macDesLabel.setText("");
        } else {
            macDesLabel.setText("Invalid Description");
            isValid = false;
        }

        try {
            Double machinePrice = Double.parseDouble(machinePriceText);
            if (machinePrice >= 0) {
                chosenGameMachine.setPrice(machinePrice);
                macPriceLabel.setText("");
            } else {
                macPriceLabel.setText("Invalid Price");
                isValid = false;
            }
        } catch (NumberFormatException e) {
            macPriceLabel.setText("Invalid Price");
            isValid = false;
        }

        if (!machineURL.isBlank()) {
            chosenGameMachine.setImage(machineURL);
            macImageLabel.setText("");
        } else {
            macImageLabel.setText("Invalid URL");
            isValid = false;
        }

        if (!machineType.isBlank()){
            chosenGameMachine.setType(machineType);
            macTypeLabel.setText("");
        }else{
            macTypeLabel.setText("Invalid Type");
            isValid = false;
        }

        if (!machineMedia.isBlank()){
            chosenGameMachine.setMedia(machineMedia);
            macMediaLabel.setText("");
        }else{
            macMediaLabel.setText("Invalid Media");
            isValid = false;
        }

        if (!machineLaunchYearText.isBlank()){
            chosenGameMachine.setLaunchYear(Integer.parseInt(machineLaunchYearText));
            macYearLabel.setText("");
        }
        else{
            macYearLabel.setText("Invalid Year");
            isValid = false;
        }
        if (manufacturer!=null){
            chosenGameMachine.setManufacturer(manufacturer);
        }else {
            isValid = false;
        }


        if (isValid) {
            gameMachineNameInput.clear();
            gameMachineDescriptionInput.clear();
            gameMachinePriceInput.clear();
            gameMachineUrlInput.clear();

            chosenGameMachine = null;
            chooseMachine.setText("");
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
        if (chosenGameMachine!=null){
            chooseMachine.setText("Selected Machine: "+chosenGameMachine.getName());
        }else{
            chooseMachine.setText("Selected Machine: null");
        }
    }
    @FXML
    private void handleTableViewPrimaryDoubleClick() throws IOException {
        Machine selectedMachine = machineTableView.getSelectionModel().getSelectedItem();
        if (selectedMachine != null) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Scenes/GameScene.fxml"));
            Parent root = loader.load();

            GameController gameController = loader.getController();
            gameController.setMachine(selectedMachine);

            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

            Stage currentStage = (Stage) machineTableView.getScene().getWindow();
            currentStage.close();
        }
    }
    public void save(){
        API.save("data.ser");
    }
    public void load(){
        API.load("data.ser");
    }
    public void clear(){
        API.clear("data.ser");
        machineTableView.getItems().clear();
    }

    public void sortByName(){
        for (int i = 0; i < 8; i++) {
            if (gameMachineHashing.hashTable[i].head != null) {
                Comparator<Machine> integerComparator = Comparator.comparing(machine -> machine.getName());
                gameMachineHashing.hashTable[i].sort(integerComparator);
                machineTableView.getItems().clear();
                gameMachineHashing.display();
            }
        }
    }
    public void sortByPrice(){
        for (int i = 0; i < 8; i++) {
            if (gameMachineHashing.hashTable[i].head != null) {
                Comparator<Machine> integerComparator = Comparator.comparing(machine -> machine.getPrice());
                gameMachineHashing.hashTable[i].sort(integerComparator);                machineTableView.getItems().clear();
                gameMachineHashing.display();
            }
        }
    }
    public void sortByDescriptionLength(){
        for (int i = 0; i < 8; i++) {
            if (gameMachineHashing.hashTable[i].head != null) {
                Comparator<Machine> integerComparator = Comparator.comparing(machine -> machine.getLaunchYear());
                gameMachineHashing.hashTable[i].sort(integerComparator);
                machineTableView.getItems().clear();
                gameMachineHashing.display();
            }
        }
    }


}


