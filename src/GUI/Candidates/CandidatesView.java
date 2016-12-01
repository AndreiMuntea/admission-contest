package GUI.Candidates;

import controller.CandidateController;
import controller.SectionController;
import domain.Candidate;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import services.CandidateServices;
import services.OptionsServices;

import java.io.IOException;

/**
 * Created by andrei on 11/20/2016.
 */
public class CandidatesView {

    CandidatesViewController controller;
    TableView<Candidate> candidatesTable;
    HBox layout;

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
    TextField sectionIDInput;

    Button addButton;
    Button deleteButton;
    Button updateButton;
    Button clearButton;
    Button registerButton;

    FXMLLoader sectionLoader;
    CandidatesSectionsController sectionsController;
    Parent sectionLayout;

    public CandidatesView(CandidateServices candidateServices, OptionsServices optionsServices) {
        sectionLoader = new FXMLLoader(CandidatesView.class.getResource("candidatesSections.fxml"));
        try {
            sectionLayout = sectionLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        sectionsController = sectionLoader.getController();
        sectionsController.setController(candidateServices, optionsServices);

        this.controller = new CandidatesViewController(this, candidateServices);
        initComponents();
    }

    private void initComponents(){
        // ---------------------------------------------- TABLE SET-UP -------------------------------------------------
        candidatesTable = new TableView<>();

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

        //Sections Input
        sectionIDInput = new TextField();
        sectionIDInput.setPromptText("Insert Section ID");

        //Input Layout
        VBox inputLayout = new VBox();
        inputLayout.getChildren().addAll(IDInput, nameInput, addressInput, phoneNumberInput, gradeInput, nameSearchInput,sectionIDInput);
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

        //Register button
        registerButton = new Button("Register");
        registerButton.setMinWidth(100);
        registerButton.setOnAction(e->controller.register());

        //Buttons Layout
        HBox buttonLayout = new HBox();
        buttonLayout.getChildren().addAll(addButton, deleteButton, updateButton, clearButton, registerButton);
        buttonLayout.setPadding(new Insets(10, 10, 10, 10));
        buttonLayout.setSpacing(40);


        //Functional Layout
        VBox functionalLayout = new VBox();
        functionalLayout.getChildren().addAll(candidatesTable, inputLayout, buttonLayout);
        functionalLayout.setPadding(new Insets(10, 10, 10, 10));



        //table initialize
        candidatesTable.getColumns().addAll(IDColumn, nameColumn, addressColumn, phoneNumberColumn, gradeColumn);
        candidatesTable.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue) -> controller.loadCandidate(newValue));
        controller.updateModel();



        //the layout set-up
        layout = new HBox();
        layout.getChildren().addAll(functionalLayout, sectionLayout);
    }

    public HBox getView() {
        return layout;
    }

}
