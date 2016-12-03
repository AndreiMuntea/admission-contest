package GUI.Sections;

import controller.SectionController;
import domain.Section;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import services.OptionsServices;
import services.SectionServices;
import utils.CustomObservableList;
import utils.MyException;
import utils.obs.AbstractObserver;
import utils.obs.Observable;

import java.io.IOException;
import java.util.List;

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
    private TextField filterByNameText;
    @FXML
    private TextField filterByMinimumSlots;
    @FXML
    private Button addButton;
    @FXML
    private Button updateButton;
    @FXML
    private Button deleteButton;
    @FXML
    private Button clearButton;
    @FXML
    private Button mostWantedButton;
    @FXML
    private Button highestAverageButton;
    @FXML
    private HBox displayLayout;

    private SectionServices service;
    private OptionsServices optionService;
    private CustomObservableList<Section> model;

    private AddButtonController addButtonController;
    private UpdateButtonController updateButtonController;
    private DeleteButtonController deleteButtonController;
    private MostWantedButtonController mostWantedButtonController;
    private HighestAverageButtonController highestAverageButtonController;

    private FXMLLoader addLoader;
    private FXMLLoader deleteLoader;
    private FXMLLoader updateLoader;
    private FXMLLoader highestAverageLoader;
    private FXMLLoader mostWantedLoader;

    private Stage addStage;
    private Stage deleteStage;
    private Stage updateStage;

    private Parent addScene;
    private Parent deleteScene;
    private Parent updateScene;
    private Parent highestAverageScene;
    private Parent mostWantedScene;

    public SectionViewController()  {
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
        addButtonController.setComponents(service, addStage);
        addStage.setScene(new Scene(addScene, 400, 400));


        deleteStage = new Stage();
        deleteStage.setTitle("Delete a section");
        deleteLoader = new FXMLLoader(SectionViewController.class.getResource("delete.fxml"));
        deleteScene = deleteLoader.load();
        deleteButtonController = deleteLoader.getController();
        deleteButtonController.setComponents(service, deleteStage);
        deleteStage.setScene(new Scene(deleteScene, 400, 400));

        updateStage = new Stage();
        updateStage.setTitle("Update a Section");
        updateLoader = new FXMLLoader(SectionViewController.class.getResource("update.fxml"));
        updateScene = updateLoader.load();
        updateButtonController = updateLoader.getController();
        updateButtonController.setComponents(service, updateStage);
        updateStage.setScene(new Scene(updateScene, 400, 400));


        highestAverageLoader = new FXMLLoader(SectionViewController.class.getResource("highestAverageSectionReport.fxml"));
        highestAverageScene = highestAverageLoader.load();
        highestAverageButtonController = highestAverageLoader.getController();
        highestAverageButtonController.setService(optionService);


        mostWantedLoader = new FXMLLoader(SectionViewController.class.getResource("mostWantedSections.fxml"));
        mostWantedScene = mostWantedLoader.load();
        mostWantedButtonController = mostWantedLoader.getController();
        mostWantedButtonController.setService(optionService);

        displayLayout.getChildren().addAll(highestAverageScene);

        model.addObserver(addButtonController);
        model.addObserver(updateButtonController);
        model.addObserver(deleteButtonController);

        filterByNameText.textProperty().addListener((e,oldValue,newValue)->inputFilterNameHandler(newValue));
        filterByMinimumSlots.textProperty().addListener((e,oldValue,newValue)->inputFilterByMinimumSlots(newValue));
    }

    public void setController(SectionServices service, OptionsServices optionServices) throws IOException {
        this.service = service;
        this.optionService = optionServices;
        service.addObserver(this);
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
        model.addAll(FXCollections.observableArrayList(service.getAll()));
        table.setItems(model);
    }

    public void updateModel(List<Section> list){
        model.clear();
        model.addAll(FXCollections.observableArrayList(list));
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
    public void update(Observable<Section> observable) {
        updateModel();
    }

    @Override
    public void update(Observable<Section> observable, Object o) {updateModel();}

    public void inputFilterNameHandler(String text)
    {
        updateModel(service.filterByPrefix(text));
    }

    public void inputFilterByMinimumSlots(String text)
    {
        try{
            updateModel(service.filterByMinimumSlots(text));
        }catch (MyException e)
        {
            Alert a = new Alert(Alert.AlertType.ERROR,e.getError());
            a.showAndWait();
        }
    }

    public void handleButtonMostWanted()
    {
        displayLayout.getChildren().clear();
        displayLayout.getChildren().add(mostWantedScene);
    }

    public void handleButtonHighestAverate()
    {
        displayLayout.getChildren().clear();
        displayLayout.getChildren().add(highestAverageScene);
    }

}
