package helpers.saver.xmlSavers;

import com.sun.org.apache.xml.internal.serializer.OutputPropertiesFactory;
import helpers.exceptions.FileException;
import helpers.saver.AbstractSaver;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.util.Collection;

/**
 * Created by andrei on 12/2/2016.
 */
public abstract class BaseXMLSaver<E> extends AbstractSaver<E> {

    public BaseXMLSaver(){}

    @Override
    public void save(Collection<E> collection, String fileName) {
        try {
            DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
            Document document = docBuilder.newDocument();

            Node root = document.createElement(getRoot());
            document.appendChild(root);
            for(E e : collection)
            {
                Element element = createElementFromFormat(document, e);
                root.appendChild(element);
            }

            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(fileName);
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty(OutputPropertiesFactory.S_KEY_INDENT_AMOUNT, "2");
            transformer.transform(source,result);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public abstract Element createElementFromFormat(Document document, E element) throws FileException;

    public abstract String getRoot();
}
