package GUI.Sections;

import controller.SectionController;
import domain.Section;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import utils.MyException;

/**
 * Created by andrei on 11/24/2016.
 */
public class SectionViewController {
    @FXML
    private TableView<Section> table;
    @FXML
    private TableColumn<Section, String> idColumn;
    @FXML
    private TableColumn<Section, String> nameColumn;
    @FXML
    private TableColumn<Section, Integer> slotsColumn;
    @FXML
    private TextField idText;
    @FXML
    private TextField nameText;
    @FXML
    private TextField slotsText;
    @FXML
    private Button addButton;
    @FXML
    private Button updateButton;
    @FXML
    private Button deleteButton;
    @FXML
    private Button clearButton;

    private SectionController controller;
    private ObservableList<Section> model;

    public SectionViewController() {
    }

    private void initialize()
    {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("ID"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        slotsColumn.setCellValueFactory(new PropertyValueFactory<>("availableSlots"));
    }

    public void setController(SectionController controller) {
        this.controller = controller;
        initialize();
        updateModel();
    }

    @FXML
    public void addButtonHandler() {
        try {
            controller.addElement(idText.getText(),
                    nameText.getText(),
                    slotsText.getText());
            updateModel();
        } catch (MyException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getError());
            alert.showAndWait();
        }
    }


    @FXML
    public void deleteButtonHandler() {
        try {
            controller.removeElement(idText.getText());
            updateModel();
        } catch (MyException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getError());
            alert.showAndWait();
        }
    }

    @FXML
    public void updateButtonHandler() {
        try {
            controller.updateElement(idText.getText(),
                    nameText.getText(),
                    slotsText.getText());
            updateModel();
        } catch (MyException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getError());
            alert.showAndWait();
        }
    }

    @FXML
    public void clearText()
    {
        idText.setText("");
        nameText.setText("");
        slotsText.setText("");
    }


    public void updateModel() {
        model = FXCollections.observableArrayList(controller.getAll());
        table.setItems(model);
    }

    public void loadSection()
    {
        Section section = table.getSelectionModel().getSelectedItem();
        if (section == null) return;
        idText.setText(section.getID());
        nameText.setText(section.getName());
        slotsText.setText(section.getAvailableSlots().toString());
    }



}
