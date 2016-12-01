package GUI.Candidates;

import domain.Candidate;
import domain.Section;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import services.CandidateServices;
import services.OptionsServices;
import utils.MyException;
import utils.obs.AbstractObserver;
import utils.obs.Observable;

/**
 * Created by andrei on 12/1/2016.
 */
public class CandidatesSectionsController extends AbstractObserver<Candidate> {

    private CandidateServices candidateService;
    private OptionsServices optionService;
    private ObservableList<Section> sections;

    @FXML
    private TableView<Section> sectionsTable;
    @FXML
    private TableColumn<Section, String> Sections;



    public CandidatesSectionsController(){}


    @Override
    public void update(Observable<Candidate> observable) {

    }

    @Override
    public void update(Observable<Candidate> observable, Object o) {
        if (o instanceof Candidate) {
            Candidate candidate = (Candidate) o;
            sections.clear();
            sections.addAll(optionService.getSectionsForCandidate(candidate.getID().toString()));
            sectionsTable.setItems(sections);
        }
    }


    public void update(Candidate candidate)
    {
        sections.clear();
        sections.addAll(optionService.getSectionsForCandidate(candidate.getID().toString()));
        sectionsTable.setItems(sections);
    }

    public void setController(CandidateServices candidateServices, OptionsServices optionsServices)
    {
        this.candidateService = candidateServices;
        this.optionService = optionsServices;
        this.sections = FXCollections.observableArrayList();
        Sections.setCellValueFactory(new PropertyValueFactory<>("name"));
    }

    public void register(String candidateID, String sectionID)
    {
        try{
            optionService.registerCandidate(candidateID, sectionID);
            update(candidateService.getCandidate(candidateID));

        }catch(MyException e)
        {
            Alert a = new Alert(Alert.AlertType.ERROR, e.getError());
            a.showAndWait();
        }
    }

}
