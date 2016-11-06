package helpers.loader.serialisedLoader;

import helpers.loader.AbstractLoader;
import helpers.loader.ILoader;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.StreamCorruptedException;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by andrei on 11/5/2016.
 */
public class BaseSerialisedLoader<Element> extends AbstractLoader<Element> implements ILoader<Element> {

    public BaseSerialisedLoader() {
    }

    @Override
    public Collection<Element> load(String fileName) {

        ArrayList<Element> elements = new ArrayList<Element>();

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName))) {
            //noinspection unchecked
            elements = (ArrayList<Element>) in.readObject();

        } catch (IOException | ClassNotFoundException e) {
            elements.clear();
            e.printStackTrace();
        }
        return elements;
    }
}
