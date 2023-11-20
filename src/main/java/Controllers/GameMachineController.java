package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import models.Machine;
import storing.Hashing;
import storing.LinkedList;
import utils.ManufacturerUtil;
import utils.VariableUtility;

import java.net.URL;
import java.util.Collection;

public class GameMachineController {

    private Machine chosenGameMachine;
    @FXML
    private TableView<Machine> machineTableView;
    private ManufacturerUtil manufacturer;





    public static Hashing<Machine> gameMachineHashing = new Hashing<>(50);
    //////////////////////////////////////////////////////////////////////////
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
    @FXML
    private TextField gameMachineNameInput;
    @FXML
    private TextField gameMachineDescriptionInput;
    @FXML
    private TextField gameMachinePriceInput;
    @FXML
    private TextField gameMachineUrlInput;

    @FXML
    private ComboBox<String> comboType;
    @FXML
    private ComboBox<String> comboMedia;
    @FXML
    private ComboBox<String> comboLaunchYear;

    //////////////////////////////////////////////////////////////////////////

    public void setManufacturer(ManufacturerUtil manufacturer){
        this.manufacturer=manufacturer;
    }

    @FXML
    public void initialize() {
        machineTableView.setOnMouseClicked(mouseEvent -> {
            if (mouseEvent.getClickCount() == 2 && mouseEvent.getButton() == MouseButton.SECONDARY) {
                chosenGameMachine = machineTableView.getSelectionModel().getSelectedItem();
            }
        });
        comboType.getItems().addAll((Collection<? extends String>) VariableUtility.types);
        comboMedia.getItems().addAll((Collection<? extends String>) VariableUtility.media);
        comboLaunchYear.getItems().addAll((Collection<? extends String>) VariableUtility.launchYear);
    }

    public void addGameMachineButton(ActionEvent event){
        String machineName = gameMachineNameInput.getText();
        String machineDescription = gameMachineDescriptionInput.getText();
        double machinePrice = Double.parseDouble(gameMachinePriceInput.getText());
        String machineURL = gameMachineUrlInput.getText();
        String machineType = comboType.getValue();
        String machineMedia = comboMedia.getValue();
        int machineLaunchYear = Integer.parseInt(comboLaunchYear.getValue());
        if (machineName.isBlank() || machineDescription.isBlank() || machinePrice<0 || machineURL.isBlank() || machineType.isBlank() || machineMedia.isBlank() ||machineLaunchYear>2024 || machineLaunchYear<1950){
            System.out.println("Please fill in all fields.");
        }
        else{
            Machine machine = new Machine(machineName,manufacturer,machineDescription,machineType,machineMedia,machineLaunchYear,machinePrice,machineURL);
            gameMachineHashing.add(machine);
            System.out.println(machine+"is added");
            gameMachineHashing.display();
        }



    }

}


