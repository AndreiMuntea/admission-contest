package GUI;

import controller.CandidateController;
import controller.SectionController;
import javafx.application.Platform;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


import java.io.IOException;

/**
 * Created by andrei on 11/20/2016.
 */
public class GUI {

    private Stage mainStage;
    private GUIController controller;
    private FXMLLoader GUILoader;
    private Parent mainScene;

    public GUI(Stage mainStage, CandidateController candidateController, SectionController sectionController) throws IOException {
        this.mainStage = mainStage;

        GUILoader = new FXMLLoader(GUI.class.getResource("gui.fxml"));
        mainScene = GUILoader.load();
        controller = GUILoader.getController();
        controller.initialiseComponents(candidateController, sectionController);
    }

    public void start(){
        mainStage.setTitle("Application");
        mainStage.setScene(new Scene(mainScene, 600, 600));
        mainStage.show();
        mainStage.setOnCloseRequest(e -> Platform.exit());

    }
}
