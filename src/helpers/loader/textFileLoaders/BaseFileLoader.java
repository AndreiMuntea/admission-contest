package helpers.loader.textFileLoaders;

import helpers.exceptions.FileException;
import helpers.loader.AbstractLoader;
import helpers.loader.ILoader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by andrei on 11/5/2016.
 */
public abstract class BaseFileLoader<Element> extends AbstractLoader<Element> implements ILoader<Element> {

    protected String separator;
    private static final String DEFAULT_SEPARATOR = "|";

    public BaseFileLoader() {
        this(DEFAULT_SEPARATOR);
    }

    public BaseFileLoader(String separator) {
        this.separator = separator;
    }

    public String getSeparator() {
        return separator;
    }

    public void setSeparator(String separator) {
        this.separator = separator;
    }

    @Override
    public Collection<Element> load(String fileName) {

        Collection<Element> elements = new ArrayList<>();

        try(BufferedReader in = new BufferedReader(new FileReader(fileName)))
        {
            String line;
            while((line = in.readLine()) != null)
            {
                Element object = createFromFormat(line);
                elements.add(object);
            }

            in.close();
        } catch (IOException | FileException e) {
            elements.clear();
            e.printStackTrace();
        }

        return elements;
    }

    public abstract Element createFromFormat(String part) throws FileException;
}
