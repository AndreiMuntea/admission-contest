package GUI.Sections;

import controller.SectionController;
import domain.Section;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import services.SectionServices;
import utils.MyException;
import utils.obs.AbstractObserver;
import utils.obs.Observable;

/**
 * Created by andrei on 11/25/2016.
 */
public class DeleteButtonController extends AbstractObserver<Section> {
    @FXML
    TextField textID;

    @FXML
    Button deleteButton;

    private SectionServices service;
    private Stage stage;

    public DeleteButtonController() {

    }

    @Override
    public void update(Observable<Section> observable, Object object) {
        if (object instanceof Section) {
            Section section = (Section) object;
            setSection(section);
        } else {
            clearText();
        }
    }

    @Override
    public void update(Observable<Section> observable) {

    }

    public void setComponents(SectionServices service, Stage stage) {
        this.service = service;
        this.stage = stage;
    }

    public void handleDeleteButton() {
        try {
            service.removeSection(textID.getText());
            stage.close();
        } catch (MyException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
            alert.showAndWait();
        }
    }

    private void clearText() {
        textID.setText("");
    }

    private void setSection(Section section) {
        if (section == null) clearText();
        else {
            textID.setText(section.getID());
        }
    }
}
