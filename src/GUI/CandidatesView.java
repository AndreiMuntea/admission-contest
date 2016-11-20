package GUI;

import controller.CandidateController;
import domain.Candidate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import utils.MyException;

import java.util.List;

/**
 * Created by andrei on 11/20/2016.
 */
public class CandidatesView {

    private CandidateController controller;
    private TableView<Candidate> candidatesTable;
    private ObservableList<Candidate> model;
    private VBox layout;

    private TableColumn<Candidate, Integer> IDColumn;
    private TableColumn<Candidate, String> nameColumn;
    private TableColumn<Candidate, String> addressColumn;
    private TableColumn<Candidate, String> phoneNumberColumn;
    private TableColumn<Candidate, Double> gradeColumn;

    private TextField IDInput;
    private TextField nameInput;
    private TextField addressInput;
    private TextField phoneNumberInput;
    private TextField gradeInput;
    private TextField nameSearchInput;

    private Button addButton;
    private Button deleteButton;
    private Button updateButton;
    private Button clearButton;


    public CandidatesView(CandidateController controller) {
        this.controller = controller;
        initComponents();
    }

    private void initComponents() {
        // ---------------------------------------------- TABLE SET-UP -------------------------------------------------

        //ID Column
        IDColumn = new TableColumn<>("ID");
        IDColumn.setMinWidth(50);
        IDColumn.setCellValueFactory(new PropertyValueFactory<>("ID"));

        //name Column
        nameColumn = new TableColumn<>("Name");
        nameColumn.setMinWidth(150);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        //address Column
        addressColumn = new TableColumn<>("Address");
        addressColumn.setMinWidth(200);
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));

        //phone number Column
        phoneNumberColumn = new TableColumn<>("Phone");
        phoneNumberColumn.setMinWidth(50);
        phoneNumberColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));

        //grade Column
        gradeColumn = new TableColumn<>("Grade");
        gradeColumn.setMinWidth(20);
        gradeColumn.setCellValueFactory(new PropertyValueFactory<>("grade"));

        //table initialize
        candidatesTable = new TableView<>();
        candidatesTable.getColumns().addAll(IDColumn, nameColumn, addressColumn, phoneNumberColumn, gradeColumn);
        candidatesTable.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue) -> loadCandidate(newValue));
        updateModel();


        // ---------------------------------------- BUTTONS AND INPUTS -----------------------------------------------

        //ID input
        IDInput = new TextField();
        IDInput.setPromptText("ID");

        //Name input
        nameInput = new TextField();
        nameInput.setPromptText("Name");

        //Address input
        addressInput = new TextField();
        addressInput.setPromptText("Address");

        //Phone number input
        phoneNumberInput = new TextField();
        phoneNumberInput.setPromptText("Phone Number");

        //Grade input
        gradeInput = new TextField();
        gradeInput.setPromptText("Grade");

        //Search input
        nameSearchInput = new TextField();
        nameSearchInput.setPromptText("Search by name");
        nameSearchInput.textProperty().addListener((v,oldValue,newValue)->filterList(newValue));

        //Input Layout
        VBox inputLayout = new VBox();
        inputLayout.getChildren().addAll(IDInput, nameInput, addressInput, phoneNumberInput, gradeInput, nameSearchInput);
        inputLayout.setPadding(new Insets(10, 10, 10, 10));
        inputLayout.setSpacing(5);


        //Add button
        addButton = new Button("Add");
        addButton.setMinWidth(100);
        addButton.setOnAction(e -> addButtonHandle());

        //Delete button
        deleteButton = new Button("Delete");
        deleteButton.setMinWidth(100);
        deleteButton.setOnAction(e -> deleteButtonHandle());

        //Update button
        updateButton = new Button("Update");
        updateButton.setMinWidth(100);
        updateButton.setOnAction(e -> updateButtonHandle());

        //Clear button
        clearButton = new Button("Clear");
        clearButton.setMinWidth(100);
        clearButton.setOnAction(e -> clearText());

        //Buttons Layout
        HBox buttonLayout = new HBox();
        buttonLayout.getChildren().addAll(addButton, deleteButton, updateButton, clearButton);
        buttonLayout.setPadding(new Insets(10, 10, 10, 10));
        buttonLayout.setSpacing(40);


        //Functional Layout
        VBox functionalLayout = new VBox();
        functionalLayout.getChildren().addAll(inputLayout, buttonLayout);
        functionalLayout.setPadding(new Insets(10, 10, 10, 10));

        //the layout set-up
        layout = new VBox();
        layout.getChildren().addAll(candidatesTable, functionalLayout);
    }

    public VBox getView() {
        return layout;
    }


    private void updateModel() {
        ObservableList<Candidate> newList = FXCollections.observableArrayList();
        newList.addAll(controller.getAll());
        model = newList;
        candidatesTable.setItems(model);
    }

    private void updateModel(List<Candidate> list) {
        ObservableList<Candidate> newList = FXCollections.observableArrayList();
        newList.addAll(list);
        model = newList;
        candidatesTable.setItems(model);
    }

    private void clearText() {
        IDInput.setText("");
        nameInput.setText("");
        addressInput.setText("");
        gradeInput.setText("");
        phoneNumberInput.setText("");
        nameSearchInput.setText("");
    }

    private void addButtonHandle() {
        try {
            controller.addElement(IDInput.getText(),
                    nameInput.getText(),
                    addressInput.getText(),
                    phoneNumberInput.getText(),
                    gradeInput.getText());
            updateModel();
        } catch (MyException e) {
            AlertBox.display("Error!", e.getError());
        }
    }

    private void deleteButtonHandle() {
        try {
            controller.removeElement(IDInput.getText());
            updateModel();
        } catch (MyException e) {
            AlertBox.display("Error!", e.getError());
        }
    }

    private void updateButtonHandle() {
        try {
            controller.updateElement(IDInput.getText(),
                    nameInput.getText(),
                    addressInput.getText(),
                    phoneNumberInput.getText(),
                    gradeInput.getText());
            updateModel();
        } catch (MyException e) {
            AlertBox.display("Error!", e.getError());
        }
    }

    private void loadCandidate(Candidate cand) {
        if (cand == null) return;
        IDInput.setText(cand.getID().toString());
        nameInput.setText(cand.getName());
        addressInput.setText(cand.getAddress());
        gradeInput.setText(cand.getGrade().toString());
        phoneNumberInput.setText(cand.getPhoneNumber());
    }

    private void filterList(String prefix) {
        updateModel(controller.filterByPrefix(prefix));
    }
}
