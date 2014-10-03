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

import pojos.HealthProfile;
import pojos.Person;

public class HealthProfileReaderExtended {
	Document document;
    XPath xpath;
    
    final static String xmlFile = "people.xml";    
	
	public HealthProfileReaderExtended() throws ParserConfigurationException, SAXException, IOException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);
        
        DocumentBuilder builder = factory.newDocumentBuilder();
        document = builder.parse(xmlFile);
        
        xpath = XPathFactory.newInstance().newXPath();
    }
    
    public double getWeight(Person person) throws XPathExpressionException {
	    if (person == null) {
	        throw new IllegalArgumentException("Person cannot be not null");
	    }
	    String expression = "/people/person[firstname = '" + person.getFirstname() + 
	    "' and lastname = '" + person.getLastname() +"']/healthprofile/weight";
	    System.out.println("Getting weight..");
	    System.out.println("using xpath = " + expression);
	    XPathExpression expr = xpath.compile(expression);
        Double weight = (Double) expr.evaluate(document, XPathConstants.NUMBER);
        return weight;
	}
	
	public double getHeight(Person person) throws XPathExpressionException {
	    if (person == null) {
	        throw new IllegalArgumentException("Person cannot be null");
	    }
    	String expression = "/people/person[firstname = '" + person.getFirstname() + 
	    "' and lastname = '" + person.getLastname() +"']/healthprofile/height";
	    System.out.println("Getting height..");
	    System.out.println("using xpath = " + expression);
	    XPathExpression expr = xpath.compile(expression);
        Double height = (Double) expr.evaluate(document, XPathConstants.NUMBER);
        return height;
	}
	
	public void printPeople(int page, int size) {
	    try {
	        System.out.println("Printing " + size + " people in page " + page + "..");
	        NodeList nodeList = this.getPeople(page, size);
	        this.printPeople(nodeList);
	    } catch(XPathExpressionException e) {
	        System.out.println(e);
	    }
	}
	
	public NodeList getPeople(int page, int size) throws XPathExpressionException {
	    if (page < 0) {
	        throw new IllegalArgumentException("Page cannot be negative");
	    }
	    if (size < 1) {
	        throw new IllegalArgumentException("Size must be at least one");
	    }
	    int startIndex = (size + 1) * page;
	    int stopIndex = startIndex + size + 1;
        String expression = "/people/person[position() >= '" + startIndex + "' and " +
        "position() < '" + stopIndex  + "']";
	    System.out.println("using xpath = " + expression);
        XPathExpression expr = xpath.compile(expression);
        NodeList nodes = (NodeList) expr.evaluate(document, XPathConstants.NODESET);
        return nodes;

    }
    
    private void printHealthProfile(String fullname) {
        try {
	        System.out.println("Printing healthprofile of " + fullname + "..");
	        HealthProfile hp = this.getHealthProfile(fullname);
	        System.out.println(hp.toString());
	    } catch(XPathExpressionException e) {
	        System.out.println(e);
	    }
	}
	
	public HealthProfile getHealthProfile(String fullname) throws XPathExpressionException {
	    if (fullname == null) {
	        throw new IllegalArgumentException("Fullname cannot be not null");
	    }	    
	    String[] names = fullname.split(" ");
	    if (names.length < 2) {
	        throw new IllegalArgumentException("Fullname must contain at least a first and last name");
	    }
	    String firstname = names[0];
	    String lastname = names[names.length - 1];
	    String expression = "/people/person[firstname = '" + firstname + "' and lastname = '" + lastname +"']/healthprofile";
	    System.out.println("using xpath = " + expression);
	    XPathExpression expr = xpath.compile(expression);
        Node node = (Node) expr.evaluate(document, XPathConstants.NODE);
        
        return new HealthProfile(node);
	}
	
	public void printPeopleByWeight(String condition, double weight) {
	    try {
    	    System.out.println("Printing people with weight " + condition + " " + weight + "..");
    	    NodeList nodeList = this.getPeopleByWeight(condition, weight);
    	    this.printPeople(nodeList);
	    } catch(XPathExpressionException e) {
	        System.out.println(e);
	    }
	}
	
	public NodeList getPeopleByWeight(String condition, double weight) throws XPathExpressionException {
	    // permitted condition <, <=, >, >=, =, !=
	    if (!condition.equals("<") &&
	        !condition.equals("<=") &&
	        !condition.equals(">") &&
	        !condition.equals(">=") &&
	        !condition.equals("=") &&
	        !condition.equals("!=")) {
	        throw new IllegalArgumentException("Permitted conditions are <, <=, >, >=, =, !=");
	    }
	    String expression = "/people/person[healthprofile/weight " + condition + " '" + weight + "']";
	    System.out.println("using xpath = " + expression);
        XPathExpression expr = xpath.compile(expression);
        NodeList nodes = (NodeList) expr.evaluate(document, XPathConstants.NODESET);
        return nodes;

    }
	
	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
        HealthProfileReaderExtended test = new HealthProfileReaderExtended();
        
        // getHeight and getWeight by Person
        try {
            Person person = new Person("Adriana", "Lima");
            double w = test.getWeight(person);
            double h = test.getHeight(person);
            person.setHealthProfile(new HealthProfile(w, h));
            System.out.println(person.toString());
        } catch(XPathExpressionException e) {
	        System.out.println(e);
	    }
	    System.out.println("");
        
        // Print people paginated
        // first page
        test.printPeople(0, 20);
        // second page
        test.printPeople(1, 20);
        System.out.println("");
        
        // Print health profile
        test.printHealthProfile("Vin Diesel");
        System.out.println("");
        
        // print people weighter less 50 kg
        test.printPeopleByWeight("<", 50);
    }
    
	private void printPeople(NodeList nodeList) {
	    for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            Person person = new Person(node);
            System.out.println(person.toString());
        }
	}
}
