package helpers.saver.serialisedSaver;

import helpers.saver.AbstractSaver;
import helpers.saver.ISaver;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by andrei on 11/5/2016.
 */
public class BaseSerialisedSaver<Element> extends AbstractSaver<Element> implements ISaver<Element> {

    public BaseSerialisedSaver(){}

    @Override
    public void save(Collection<Element> collection, String fileName) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName))) {

            ArrayList elements = new ArrayList<>();
            //noinspection unchecked
            elements.addAll(collection);

            out.writeObject(elements);
            out.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

