
import controller.CandidateController;
import controller.IController;
import domain.Candidate;
import domain.Section;
import helpers.loader.ILoader;
import helpers.loader.serialisedLoader.SerialisedCandidateLoader;
import helpers.loader.serialisedLoader.SerialisedSectionLoader;
import helpers.loader.textFileLoaders.CandidateFileLoader;
import helpers.saver.ISaver;
import helpers.saver.serialisedSaver.SerialisedCandidateSaver;
import helpers.saver.serialisedSaver.SerialisedSectionSaver;
import helpers.saver.textFileSaver.CandidateFileSaver;
import repository.FileRepository;
import repository.IRepository;
import repository.InMemoryRepository;
import utils.MyException;
import validator.CandidateValidator;
import validator.IValidator;


/**
 * Created by munte on 11/2/2016.
 */
public class Main {

    public static void main(String args[]) throws MyException
    {
        ILoader<Candidate> loader = new CandidateFileLoader();
        ISaver<Candidate> saver = new CandidateFileSaver();
        IRepository<Integer, Candidate> repo = new FileRepository<>("resources/candidates.txt",loader, saver);
        IValidator<Candidate> validator = new CandidateValidator();
        IController<Integer, Candidate> controller = new CandidateController(validator,repo);

        System.out.println(controller.getAll());
    }

}
