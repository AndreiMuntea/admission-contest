package helpers.saver.textFileSaver;

import helpers.saver.AbstractSaver;
import helpers.saver.ISaver;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;

/**
 * Created by andrei on 11/5/2016.
 */
public abstract class BaseFileSaver<Element> extends AbstractSaver<Element> implements ISaver<Element> {

    protected String separator;
    private static final String DEFAULT_SEPARATOR = "|";

    public BaseFileSaver(){
        this(DEFAULT_SEPARATOR);
    }

    public BaseFileSaver(String separator)
    {
        this.separator = separator;
    }

    public String getSeparator() {
        return separator;
    }

    public void setSeparator(String separator) {
        this.separator = separator;
    }

    @Override
    public void save(Collection<Element> collection, String fileName) {

        try (BufferedWriter out = new BufferedWriter(new FileWriter(fileName))) {
            for (Element obj : collection) {
                String line = getFormat(obj);
                out.write(line + "\n");
            }
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public abstract String getFormat(Element object);
}
