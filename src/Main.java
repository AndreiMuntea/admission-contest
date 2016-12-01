
import GUI.GUI;
import controller.CandidateController;
import controller.OptionController;
import controller.SectionController;
import domain.Candidate;
import domain.Option;
import domain.Section;
import helpers.loader.ILoader;
import helpers.loader.serialisedLoader.SerialisedSectionLoader;
import helpers.loader.textFileLoaders.CandidateFileLoader;
import helpers.loader.textFileLoaders.OptionFileLoader;
import helpers.loader.textFileLoaders.SectionFileLoader;
import helpers.saver.ISaver;
import helpers.saver.serialisedSaver.SerialisedSectionSaver;
import helpers.saver.textFileSaver.CandidateFileSaver;
import helpers.saver.textFileSaver.OptionFileSaver;
import helpers.saver.textFileSaver.SectionFileSaver;
import javafx.application.Application;
import javafx.stage.Stage;
import repository.FileRepository;
import repository.IRepository;
import utils.Pair;
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


        ILoader<Option> optionLoader = new OptionFileLoader();
        ISaver<Option>  optionSaver = new OptionFileSaver();
        IRepository<Pair<Integer,String>,Option> optionRepository = new FileRepository<>("resources/options.txt",optionLoader, optionSaver);
        OptionController optionController = new OptionController(candidateRepository, sectionRepository, optionRepository);

        GUI gui = new GUI(primaryStage, candidateController, sectionController, optionController);
        gui.start();

    }
}
