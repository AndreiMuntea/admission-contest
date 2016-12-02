package helpers.loader.xmlLoaders;

import domain.Section;
import helpers.exceptions.FileException;
import org.w3c.dom.Element;

/**
 * Created by andrei on 12/2/2016.
 */
public class SectionXMLLoader extends BaseXMLLoader<Section> {

    public SectionXMLLoader(){}

    @Override
    public Section createFromFormat(Element element) throws FileException {
        try{
            String sectionID = element.getElementsByTagName("ID").item(0).getTextContent();
            String sectionName = element.getElementsByTagName("name").item(0).getTextContent();
            Integer slots = Integer.parseInt(element.getElementsByTagName("slots").item(0).getTextContent());
            return new Section(sectionID, sectionName, slots);
        }catch (NumberFormatException e)
        {
            throw new FileException("Corrupted file!\n");
        }
    }
}
