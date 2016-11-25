package GUI;

import GUI.Candidates.CandidatesView;
import GUI.Sections.SectionViewController;
import controller.CandidateController;
import controller.SectionController;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;

/**
 * Created by andrei on 11/20/2016.
 */
public class GUI {

    private CandidateController candidateController;
    private SectionController sectionController;
    private Stage mainStage;
    private CandidatesView candidatesView;
    private SectionViewController sectionViewController;
    private FXMLLoader sectionLoader;
    private Parent sectionScene;
    private VBox candidatesScene;

    private MenuItem sectionMenu;
    private MenuItem candidatesMenu;


    public GUI(Stage mainStage, CandidateController candidateController, SectionController sectionController) throws IOException {
        this.mainStage = mainStage;
        this.candidateController = candidateController;
        this.sectionController = sectionController;

        candidatesView = new CandidatesView(candidateController);
        candidatesScene = candidatesView.getView();

        sectionLoader = new FXMLLoader(GUI.class.getResource("Sections/sections.fxml"));
        sectionScene = sectionLoader.load();

        sectionViewController = sectionLoader.getController();
        sectionViewController.setController(sectionController);
    }

    public void start(){
        mainStage.setTitle("Sections");
        Scene mainScene = new Scene(sectionScene, 600, 600);
        mainStage.setScene(mainScene);
        mainStage.show();
        mainStage.setOnCloseRequest(e -> Platform.exit());

    }
}
