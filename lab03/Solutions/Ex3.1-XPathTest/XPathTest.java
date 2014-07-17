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
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XPathTest {

	  public static void main(String[] args)  throws ParserConfigurationException, SAXException,
	          											IOException, XPathExpressionException {

	    DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
	    domFactory.setNamespaceAware(true);
	    DocumentBuilder builder = domFactory.newDocumentBuilder();
	    System.out.println("Loading books.xml...");
	    Document doc = builder.parse("books.xml");

	    XPathFactory factory = XPathFactory.newInstance();
	    XPath xpath = factory.newXPath();
	    System.out.println("Reading list of titles...");
	    XPathExpression expr = xpath.compile("/bookstore/book/title/text()");

	    System.out.println("Reading list of authors...");
	    XPathExpression expr2 = xpath.compile("/bookstore/book/author/text()");

	    Object result = expr.evaluate(doc, XPathConstants.NODESET);
	    Object result2 = expr2.evaluate(doc, XPathConstants.NODESET);

	    NodeList nodes = (NodeList) result;
	    NodeList nodes2 = (NodeList) result2;
	    for (int i = 0; i < nodes.getLength(); i++) {
	        System.out.print(nodes.item(i).getNodeValue()+" written by ");
	        System.out.println(nodes2.item(i).getNodeValue());
	    }

	  }
}
