package helpers.loader.xmlLoaders;

import helpers.exceptions.FileException;
import helpers.loader.AbstractLoader;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by andrei on 12/2/2016.
 */
public abstract class BaseXMLLoader<E> extends AbstractLoader<E> {


    public BaseXMLLoader() {
    }

    @Override
    public  Collection<E> load(String fileName)
    {
        Collection<E> elements = new ArrayList<>();

        try{
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = dbFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(new FileInputStream(fileName));

            Node root = doc.getDocumentElement();
            NodeList nodeList = root.getChildNodes();

            for(int i = 0; i < nodeList.getLength(); ++i)
            {
                if(nodeList.item(i).getNodeType() == Node.ELEMENT_NODE )
                {
                    Element e = (Element) nodeList.item(i);
                    E element = createFromFormat(e);
                    elements.add(element);
                }
            }

        }catch(Exception e)
        {
            e.printStackTrace();
            elements.clear();
        }
        return elements;
    }

    public abstract E createFromFormat(Element element) throws FileException;

}
