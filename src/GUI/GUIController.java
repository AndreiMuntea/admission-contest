package GUI;

import GUI.Candidates.CandidatesView;
import GUI.Sections.SectionViewController;
import controller.CandidateController;
import controller.OptionController;
import controller.SectionController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import services.CandidateServices;
import services.OptionsServices;
import services.SectionServices;

import java.io.IOException;

/**
 * Created by andrei on 11/28/2016.
 */
public class GUIController {
    @FXML
    Button candidatesButton;

    @FXML
    Button sectionsButton;

    @FXML
    VBox windowDisplayLayout;

    private CandidatesView candidatesView;
    private SectionViewController sectionViewController;
    private FXMLLoader sectionLoader;
    private Parent sectionScene;
    private Parent candidatesScene;

    private CandidateServices candidateServices;
    private SectionServices sectionServices;
    private OptionsServices optionsServices;

    public GUIController()
    {

    }

    public void initialiseComponents(CandidateServices candidateServices, SectionServices sectionServices, OptionsServices optionsServices) throws IOException {

        this.candidateServices = candidateServices;
        this.sectionServices = sectionServices;

        candidatesView = new CandidatesView(candidateServices, optionsServices);
        candidatesScene = candidatesView.getView();

        sectionLoader = new FXMLLoader(GUIController.class.getResource("Sections/sections.fxml"));
        sectionScene = sectionLoader.load();

        sectionViewController = sectionLoader.getController();
        sectionViewController.setController(sectionServices, optionsServices);

        windowDisplayLayout.getChildren().add(candidatesScene);
        candidatesButton.setDisable(true);
    }

    public void handleCandidatesButton()
    {
        candidatesButton.setDisable(true);
        sectionsButton.setDisable(false);

        windowDisplayLayout.getChildren().clear();
        windowDisplayLayout.getChildren().add(candidatesScene);
    }


    public void handleSectionsButton()
    {
        candidatesButton.setDisable(false);
        sectionsButton.setDisable(true);

        windowDisplayLayout.getChildren().clear();
        windowDisplayLayout.getChildren().add(sectionScene);
    }







}
