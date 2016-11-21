package GUI.Candidates;

import controller.CandidateController;
import domain.Candidate;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * Created by andrei on 11/20/2016.
 */
public class CandidatesView {

    CandidatesViewController controller;
    TableView<Candidate> candidatesTable;
    VBox layout;

    TableColumn<Candidate, Integer> IDColumn;
    TableColumn<Candidate, String> nameColumn;
    TableColumn<Candidate, String> addressColumn;
    TableColumn<Candidate, String> phoneNumberColumn;
    TableColumn<Candidate, Double> gradeColumn;

    TextField IDInput;
    TextField nameInput;
    TextField addressInput;
    TextField phoneNumberInput;
    TextField gradeInput;
    TextField nameSearchInput;

    Button addButton;
    Button deleteButton;
    Button updateButton;
    Button clearButton;


    public CandidatesView(CandidateController ctr) {
        this.controller = new CandidatesViewController(this, ctr);
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
        candidatesTable.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue) -> controller.loadCandidate(newValue));
        controller.updateModel();


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
        nameSearchInput.textProperty().addListener((v,oldValue,newValue)->controller.filterList(newValue));

        //Input Layout
        VBox inputLayout = new VBox();
        inputLayout.getChildren().addAll(IDInput, nameInput, addressInput, phoneNumberInput, gradeInput, nameSearchInput);
        inputLayout.setPadding(new Insets(10, 10, 10, 10));
        inputLayout.setSpacing(5);


        //Add button
        addButton = new Button("Add");
        addButton.setMinWidth(100);
        addButton.setOnAction(e -> controller.addButtonHandle());

        //Delete button
        deleteButton = new Button("Delete");
        deleteButton.setMinWidth(100);
        deleteButton.setOnAction(e -> controller.deleteButtonHandle());

        //Update button
        updateButton = new Button("Update");
        updateButton.setMinWidth(100);
        updateButton.setOnAction(e -> controller.updateButtonHandle());

        //Clear button
        clearButton = new Button("Clear");
        clearButton.setMinWidth(100);
        clearButton.setOnAction(e -> controller.clearText());

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

}
