package GUI.Candidates;

import GUI.AlertBox;
import controller.CandidateController;
import domain.Candidate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.MyException;

import java.util.List;

/**
 * Created by andrei on 11/21/2016.
 */
public class CandidatesViewController {

    CandidatesView view;
    CandidateController controller;
    private ObservableList<Candidate> model;

    public CandidatesViewController(CandidatesView view, CandidateController controller)
    {
        this.view = view;
        this.controller = controller;
    }

    public void updateModel() {
        ObservableList<Candidate> newList = FXCollections.observableArrayList();
        newList.addAll(controller.getAll());
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
            controller.addElement(view.IDInput.getText(),
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
            controller.removeElement(view.IDInput.getText());
            updateModel();
        } catch (MyException e) {
            AlertBox.display("Error!", e.getError());
        }
    }

    public void updateButtonHandle() {
        try {
            controller.updateElement(view.IDInput.getText(),
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
        view.IDInput.setText(cand.getID().toString());
        view.nameInput.setText(cand.getName());
        view.addressInput.setText(cand.getAddress());
        view.gradeInput.setText(cand.getGrade().toString());
        view.phoneNumberInput.setText(cand.getPhoneNumber());
    }

    public void filterList(String prefix) {
        updateModel(controller.filterByPrefix(prefix));
    }
}
