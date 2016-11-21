package GUI;

import GUI.Candidates.CandidatesView;
import controller.CandidateController;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Created by andrei on 11/20/2016.
 */
public class GUI {

    private CandidateController candidateController;
    private Stage mainStage;
    private CandidatesView candidatesView;

    public GUI(Stage mainStage, CandidateController candidateController)
    {
        this.mainStage = mainStage;
        this.candidateController = candidateController;
        candidatesView = new CandidatesView(candidateController);
    }

    public void start()
    {
        mainStage.setTitle("Candidates");
        Scene mainScene = new Scene(candidatesView.getView(), 600, 600);

        mainStage.setScene(mainScene);
        mainStage.show();
    }

}
