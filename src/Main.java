
import GUI.GUI;
import controller.CandidateController;
import controller.SectionController;
import domain.Candidate;
import domain.Section;
import helpers.loader.ILoader;
import helpers.loader.textFileLoaders.CandidateFileLoader;
import helpers.loader.textFileLoaders.SectionFileLoader;
import helpers.saver.ISaver;
import helpers.saver.textFileSaver.CandidateFileSaver;
import helpers.saver.textFileSaver.SectionFileSaver;
import javafx.application.Application;
import javafx.stage.Stage;
import repository.FileRepository;
import repository.IRepository;
import utils.MyException;
import validator.CandidateValidator;
import validator.IValidator;
import validator.SectionValidator;


/**
 * Created by munte on 11/2/2016.
 */
public class Main extends Application {

    public static void main(String args[]) throws Exception
    {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        IValidator<Candidate> candidateValidator = new CandidateValidator();
        ILoader<Candidate> candidateLoader = new CandidateFileLoader();
        ISaver<Candidate> candidateSaver = new CandidateFileSaver();
        IRepository<Integer, Candidate> candidateRepository = new FileRepository<>("resources/candidates.txt", candidateLoader, candidateSaver);
        CandidateController candidateController = new CandidateController(candidateValidator,candidateRepository);


        IValidator<Section> sectionValidator = new SectionValidator();
        ILoader<Section> sectionLoader = new SectionFileLoader();
        ISaver<Section> sectionSaver = new SectionFileSaver();
        IRepository<String, Section> sectionRepository = new FileRepository<>("resources/sections.txt", sectionLoader, sectionSaver);
        SectionController sectionController = new SectionController(sectionValidator, sectionRepository);

        GUI gui = new GUI(primaryStage, candidateController, sectionController);
        gui.start();

    }
}
