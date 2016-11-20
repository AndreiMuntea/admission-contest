
import GUI.GUI;
import controller.CandidateController;
import domain.Candidate;
import helpers.loader.ILoader;
import helpers.loader.textFileLoaders.CandidateFileLoader;
import helpers.saver.ISaver;
import helpers.saver.textFileSaver.CandidateFileSaver;
import javafx.application.Application;
import javafx.stage.Stage;
import repository.FileRepository;
import repository.IRepository;
import utils.MyException;
import validator.CandidateValidator;
import validator.IValidator;


/**
 * Created by munte on 11/2/2016.
 */
public class Main extends Application {

    public static void main(String args[]) throws MyException
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

        GUI gui = new GUI(primaryStage, candidateController);
        gui.start();

    }
}
