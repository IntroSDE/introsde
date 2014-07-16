
/**
 * Based on code made by Muhammad Imran
 */
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XPathTestAdvance {

    Document doc;
    XPath xpath;

    public void loadXML() throws ParserConfigurationException, SAXException, IOException {

        DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
        domFactory.setNamespaceAware(true);
        DocumentBuilder builder = domFactory.newDocumentBuilder();
        doc = builder.parse("books.xml");

        //creating xpath object
        getXPathObj();
    }

    public XPath getXPathObj() {

        XPathFactory factory = XPathFactory.newInstance();
        xpath = factory.newXPath();
        return xpath;
    }

    public Node getBookByName(String bookTitle) throws XPathExpressionException {

        XPathExpression expr = xpath.compile("/bookstore/book[title='" + bookTitle + "']");
        Node node = (Node) expr.evaluate(doc, XPathConstants.NODE);
        return node;
    }

    public Node getBookByISBN(String ISBN) throws XPathExpressionException {

        XPathExpression expr = xpath.compile("/bookstore/book[isbn='" + ISBN + "']");
        Node node = (Node) expr.evaluate(doc, XPathConstants.NODE);
        return node;
    }

    public NodeList getBooksByPrice(String price, String condition) throws XPathExpressionException {

        XPathExpression expr = xpath.compile("//book[price " + condition + "'" + price + "']");
        NodeList nodes = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);
        return nodes;

    }

    public Node getBookByAuthorUsingAxis(String authorName) throws XPathExpressionException {

        XPathExpression expr = xpath.compile("//child::book[author='" + authorName + "']");
        Node node = (Node) expr.evaluate(doc, XPathConstants.NODE);
        return node;
    }

    public static void main(String[] args) throws ParserConfigurationException, SAXException,
            IOException, XPathExpressionException {

        XPathTestAdvance test = new XPathTestAdvance();
        test.loadXML();

        //getting node by book name
        Node node = test.getBookByName("Snow Crash");
        System.out.println("Node name: " + node.getNodeName());
        System.out.println("My childs text contents :" + node.getTextContent());

        //getting node by ISBN number
        node = test.getBookByISBN("0553573862");
        System.out.println(node.getTextContent());


        //getting book by price 
        NodeList nodes = test.getBooksByPrice("10", ">");
        System.out.println("Books having price > 10");
        System.out.println("Book-1");
        System.out.println(nodes.item(0).getTextContent());
        System.out.println("Book-2");
        System.out.println(nodes.item(1).getTextContent());


        //getting book by authorName using AXIS approach
        System.out.println("Getting book by author name:");
        node = test.getBookByAuthorUsingAxis("Larry Niven");
        System.out.println(node.getTextContent());
    }
}
