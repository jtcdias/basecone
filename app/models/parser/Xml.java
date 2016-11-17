package models.parser;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import play.Logger;

/**
 * @author jdias.
 */
public class Xml {

    private static final Logger.ALogger logger = Logger.of(Xml.class);

    public static Document parse(File file) {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        try {
            return documentBuilderFactory.newDocumentBuilder().parse(file);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            logger.error(String.format("Couldn't parse xml file %s", file.getAbsolutePath()));
        }
        return null;
    }
}
