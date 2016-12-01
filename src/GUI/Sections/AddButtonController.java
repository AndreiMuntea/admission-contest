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
public class AddButtonController extends AbstractObserver<Section> {

    @FXML
    TextField textID;

    @FXML
    TextField textName;

    @FXML
    TextField textSlots;

    @FXML
    Button addButton;

    private SectionServices service;
    private Stage stage;

    public AddButtonController() {

    }

    @Override
    public void update(Observable<Section> observable) {

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

    public void setComponents(SectionServices service, Stage stage) {
        this.service = service;
        this.stage = stage;
    }

    public void handleAddButton() {
        try {
            service.addSection(textID.getText(), textName.getText(), textSlots.getText());
            stage.close();
        } catch (MyException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
            alert.showAndWait();
        }
    }

    private void clearText() {
        textID.setText("");
        textName.setText("");
        textSlots.setText("");
    }

    private void setSection(Section section) {
        if (section == null) clearText();
        else {
            textID.setText(section.getID());
            textName.setText(section.getName());
            textSlots.setText(section.getAvailableSlots().toString());
        }
    }

}
