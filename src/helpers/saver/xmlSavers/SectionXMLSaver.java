package helpers.saver.xmlSavers;

import domain.Section;
import helpers.exceptions.FileException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * Created by andrei on 12/2/2016.
 */
public class SectionXMLSaver extends BaseXMLSaver<Section> {

    private String root;
    private final static String DEFAULT_ROOT = "Sections";

    public SectionXMLSaver(){root = DEFAULT_ROOT;}

    public SectionXMLSaver(String root){this.root = root;}

    @Override
    public Element createElementFromFormat(Document document, Section element) throws FileException {
        Element sectionElement = document.createElement("Section");

        Element sectionID = document.createElement("ID");
        sectionID.setTextContent(element.getID());

        Element sectionName = document.createElement("name");
        sectionName.setTextContent(element.getName());

        Element sectionSlots = document.createElement("slots");
        sectionSlots.setTextContent(element.getAvailableSlots().toString());

        sectionElement.appendChild(sectionID);
        sectionElement.appendChild(sectionName);
        sectionElement.appendChild(sectionSlots);

        return sectionElement;
    }

    @Override
    public String getRoot() {
        return root;
    }
}
