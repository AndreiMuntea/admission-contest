package GUI.Candidates;

import GUI.AlertBox;
import controller.CandidateController;
import domain.Candidate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import services.CandidateServices;
import services.OptionsServices;
import utils.MyException;

import java.io.IOException;
import java.util.List;

/**
 * Created by andrei on 11/21/2016.
 */
public class CandidatesViewController {

    CandidatesView view;
    CandidateServices service;
    private ObservableList<Candidate> model;


    public CandidatesViewController(CandidatesView view, CandidateServices service)
    {
        this.view = view;
        this.service = service;
        this.model = FXCollections.observableArrayList();
    }

    public void updateModel() {
        ObservableList<Candidate> newList = FXCollections.observableArrayList();
        newList.addAll(service.getAll());
        model = newList;
        view.candidatesTable.setItems(model);
    }

    public void updateModel(List<Candidate> list) {
        ObservableList<Candidate> newList = FXCollections.observableArrayList();
        newList.addAll(list);
        model = newList;
        view.candidatesTable.setItems(model);
    }

    public void clearText() {
        view.IDInput.setText("");
        view.nameInput.setText("");
        view.addressInput.setText("");
        view.gradeInput.setText("");
        view.phoneNumberInput.setText("");
        view.nameSearchInput.setText("");
    }

    public void addButtonHandle() {
        try {
            service.addCandidate(view.IDInput.getText(),
                    view.nameInput.getText(),
                    view.addressInput.getText(),
                    view.phoneNumberInput.getText(),
                    view.gradeInput.getText());
            updateModel();
        } catch (MyException e) {
            AlertBox.display("Error!", e.getError());
        }
    }

    public void deleteButtonHandle() {
        try {
            service.removeCandidate(view.IDInput.getText());
            updateModel();
        } catch (MyException e) {
            AlertBox.display("Error!", e.getError());
        }
    }

    public void updateButtonHandle() {
        try {
            service.updateCandidate(view.IDInput.getText(),
                    view.nameInput.getText(),
                    view.addressInput.getText(),
                    view.phoneNumberInput.getText(),
                    view.gradeInput.getText());
            updateModel();
        } catch (MyException e) {
            AlertBox.display("Error!", e.getError());
        }
    }

    public void loadCandidate(Candidate cand) {
        if (cand == null) return;
        view.sectionsController.update(cand);
        view.IDInput.setText(cand.getID().toString());
        view.nameInput.setText(cand.getName());
        view.addressInput.setText(cand.getAddress());
        view.gradeInput.setText(cand.getGrade().toString());
        view.phoneNumberInput.setText(cand.getPhoneNumber());
    }

    public void filterList(String prefix) {
        updateModel(service.filterByPrefix(prefix));
    }

    public void register()
    {
        view.sectionsController.register(view.IDInput.getText(), view.sectionIDInput.getText());

    }
}
