package GUI.Sections;

import controller.SectionController;
import domain.Section;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import utils.MyException;
import utils.obs.AbstractObserver;
import utils.obs.Observable;

/**
 * Created by andrei on 11/25/2016.
 */
public class UpdateButtonController extends AbstractObserver<Section> {

    @FXML
    TextField textID;

    @FXML
    TextField textName;

    @FXML
    TextField textSlots;

    @FXML
    Button updateButton;

    private SectionController controller;
    private Stage stage;



    public void setComponents(SectionController controller, Stage stage) {
        this.controller = controller;
        this.stage = stage;
    }

    public void handleUpdateButton() {
        try {
            controller.updateElement(textID.getText(), textName.getText(), textSlots.getText());
            stage.close();
        } catch (MyException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
            alert.showAndWait();
        }
    }

    private void clearText()
    {
        textID.setText("");
        textName.setText("");
        textSlots.setText("");
    }

    private void setSection(Section section)
    {
        if (section == null) clearText();
        else
        {
            textID.setText(section.getID());
            textName.setText(section.getName());
            textSlots.setText(section.getAvailableSlots().toString());
        }
    }

    @Override
    public void update(Observable<Section> observable, Object... objects) {
        if(objects.length != 1) return;
        if(objects[0] instanceof Section)
        {
            Section section = (Section) objects[0];
            setSection(section);
        }
    }
}
