package GUI;

import controller.CandidateController;
import controller.OptionController;
import controller.SectionController;
import javafx.application.Platform;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import services.CandidateServices;
import services.OptionsServices;
import services.SectionServices;


import java.io.IOException;

/**
 * Created by andrei on 11/20/2016.
 */
public class GUI {

    private Stage mainStage;
    private GUIController controller;
    private FXMLLoader GUILoader;
    private Parent mainScene;

    private CandidateServices candidateServices;
    private SectionServices sectionServices;
    private OptionsServices optionsServices;

    public GUI(Stage mainStage, CandidateController candidateController, SectionController sectionController, OptionController optionController) throws IOException {
        this.mainStage = mainStage;

        this.candidateServices = new CandidateServices(candidateController);
        this.sectionServices = new SectionServices(sectionController);
        this.optionsServices = new OptionsServices(optionController, candidateController, sectionController);
        sectionServices.addObserver(optionsServices);
        candidateServices.addObserver(optionsServices);

        GUILoader = new FXMLLoader(GUI.class.getResource("gui.fxml"));
        mainScene = GUILoader.load();
        controller = GUILoader.getController();
        controller.initialiseComponents(candidateServices, sectionServices, optionsServices);
    }

    public void start(){
        mainStage.setTitle("Application");
        mainStage.setScene(new Scene(mainScene, 1100, 600));
        mainStage.setResizable(false);
        mainStage.show();
        mainStage.setOnCloseRequest(e -> Platform.exit());

    }
}
