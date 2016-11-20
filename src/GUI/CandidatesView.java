package GUI;

import controller.CandidateController;
import domain.Candidate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
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

    CandidateController controller;
    TableView<Candidate> candidatesTable;
    ObservableList<Candidate> model;
    VBox layout;

    public CandidatesView(CandidateController controller)
    {
        this.controller = controller;
        updateModel();
        initComponents();
    }

    private void initComponents()
    {
        // ---------------------------------------------- TABLE SET-UP -------------------------------------------------

        //ID Column
        TableColumn<Candidate, Integer> IDColumn = new TableColumn<>("ID");
        IDColumn.setMinWidth(50);
        IDColumn.setCellValueFactory(new PropertyValueFactory<>("ID"));

        //name Column
        TableColumn<Candidate, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setMinWidth(150);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        //address Column
        TableColumn<Candidate, String> addressColumn = new TableColumn<>("Address");
        addressColumn.setMinWidth(200);
        addressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));

        //phone number Column
        TableColumn<Candidate, String> phoneNumberColumn = new TableColumn<>("Phone");
        phoneNumberColumn.setMinWidth(50);
        phoneNumberColumn.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));

        //grade Column
        TableColumn<Candidate, String> gradeColumn = new TableColumn<>("Grade");
        gradeColumn.setMinWidth(20);
        gradeColumn.setCellValueFactory(new PropertyValueFactory<>("grade"));

        //table initialize
        candidatesTable = new TableView<>();
        candidatesTable.setItems(model);
        candidatesTable.getColumns().addAll(IDColumn,nameColumn,addressColumn,phoneNumberColumn,gradeColumn);


        // ---------------------------------------- BUTTONS AND INPUTS -----------------------------------------------

        //ID input
        TextField IDInput = new TextField();
        IDInput.setPromptText("ID");

        //Name input
        TextField nameInput = new TextField();
        nameInput.setPromptText("Name");

        //Address input
        TextField addressInput = new TextField();
        addressInput.setPromptText("Address");

        //Phone number input
        TextField phoneNumberInput = new TextField();
        phoneNumberInput.setPromptText("Phone Number");

        //Grade input
        TextField gradeInput = new TextField();
        gradeInput.setPromptText("Grade");

        //Input Layout
        VBox inputLayout = new VBox();
        inputLayout.getChildren().addAll(IDInput, nameInput, addressInput, phoneNumberInput, gradeInput);
        inputLayout.setPadding(new Insets(10,10,10,10));




        //the layout set-up
        layout = new VBox();
        layout.getChildren().addAll(candidatesTable, inputLayout);
    }

    public VBox getView(){
        return layout;
    }




    private void updateModel()
    {
        ObservableList<Candidate> newList = FXCollections.observableArrayList();
        newList.addAll(controller.getAll());
        model = newList;
    }




}
