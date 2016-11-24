package GUI;

import GUI.Candidates.CandidatesView;
import GUI.Sections.SectionViewController;
import controller.CandidateController;
import controller.SectionController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

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
    private Parent sectionStage;

    public GUI(Stage mainStage, CandidateController candidateController, SectionController sectionController) throws IOException {
        this.mainStage = mainStage;
        this.candidateController = candidateController;
        this.sectionController = sectionController;

        candidatesView = new CandidatesView(candidateController);

        sectionLoader = new FXMLLoader(GUI.class.getResource("Sections/sections.fxml"));
        sectionStage = sectionLoader.load();

        sectionViewController = sectionLoader.getController();
        sectionViewController.setController(sectionController);
    }

    public void start(){
        mainStage.setTitle("Sections");
        //Scene mainScene = new Scene(candidatesView.getView(), 600, 600);
        Scene mainScene = new Scene(sectionStage,600,600);
        mainStage.setScene(mainScene);
        mainStage.show();
    }

}
