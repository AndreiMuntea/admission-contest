package GUI.Sections;

import controller.SectionController;
import domain.Section;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import utils.CustomObservableList;
import utils.obs.AbstractObserver;
import utils.obs.Observable;

import java.io.IOException;

/**
 * Created by andrei on 11/24/2016.
 */
public class SectionViewController extends AbstractObserver<Section> {
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
    private CustomObservableList<Section> model;

    private AddButtonController addButtonController;
    private UpdateButtonController updateButtonController;
    private DeleteButtonController deleteButtonController;

    private FXMLLoader addLoader;
    private FXMLLoader deleteLoader;
    private FXMLLoader updateLoader;

    private Stage addStage;
    private Stage deleteStage;
    private Stage updateStage;

    private Parent addScene;
    private Parent deleteScene;
    private Parent updateScene;

    public SectionViewController() {
    }

    private void initialize() throws IOException {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("ID"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        slotsColumn.setCellValueFactory(new PropertyValueFactory<>("availableSlots"));
        model = new CustomObservableList<>();

        addStage = new Stage();
        addStage.setTitle("Add a section");
        addLoader = new FXMLLoader(SectionViewController.class.getResource("add.fxml"));
        addScene = addLoader.load();
        addButtonController = addLoader.getController();
        addButtonController.setComponents(controller, addStage);
        addStage.setScene(new Scene(addScene, 400, 400));


        deleteStage = new Stage();
        deleteStage.setTitle("Delete a section");
        deleteLoader = new FXMLLoader(SectionViewController.class.getResource("delete.fxml"));
        deleteScene = deleteLoader.load();
        deleteButtonController = deleteLoader.getController();
        deleteButtonController.setComponents(controller, deleteStage);
        deleteStage.setScene(new Scene(deleteScene, 400, 400));

        updateStage = new Stage();
        updateStage.setTitle("Update a Section");
        updateLoader = new FXMLLoader(SectionViewController.class.getResource("update.fxml"));
        updateScene = updateLoader.load();
        updateButtonController = updateLoader.getController();
        updateButtonController.setComponents(controller, updateStage);
        updateStage.setScene(new Scene(updateScene, 400, 400));

        model.addObserver(addButtonController);
        model.addObserver(updateButtonController);
        model.addObserver(deleteButtonController);


    }

    public void setController(SectionController controller) throws IOException {
        this.controller = controller;
        controller.addObserver(this);
        initialize();
        updateModel();
    }

    @FXML
    public void addButtonHandler() throws IOException {
        addStage.show();
    }


    @FXML
    public void deleteButtonHandler() {
        deleteStage.show();
    }

    @FXML
    public void updateButtonHandler() {
        updateStage.show();
    }

    @FXML
    public void clearText()
    {
        model.notifyObservers(null);
        idText.setText("");
        nameText.setText("");
        slotsText.setText("");
    }


    public void updateModel() {
        model.clear();
        model.addAll(FXCollections.observableArrayList(controller.getAll()));
        table.setItems(model);
    }

    public void loadSection()
    {
        Section section = table.getSelectionModel().getSelectedItem();
        if (section == null) return;
        model.notifyObservers(section);
        idText.setText(section.getID());
        nameText.setText(section.getName());
        slotsText.setText(section.getAvailableSlots().toString());
    }

    @Override
    public void update(Observable<Section> observable, Object... objects) {
        updateModel();
    }
}
