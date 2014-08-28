package introsde.rest.servlet;

import java.io.IOException;
import java.io.StringWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;


@WebServlet(name = "SydneyServlet", urlPatterns = "/sydney/places/*")
public class SydneyServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private static final String[] places = {
        "Harbour Bridge",
        "Circular Quay",
        "Opera House",
        "Hyde Park",
        "Darling Harbour",
        "Bondi Beach",
        "Coogee Beach"
    };

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if ("/json".equals(request.getPathInfo())) {
			try {
				jsonReply(response);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if ("/csv".equals(request.getPathInfo())) {
			csvReply(response);
		} else {
			try {
				xmlReply(response);
			} catch (TransformerFactoryConfigurationError e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ParserConfigurationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (TransformerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	private void xmlReply(HttpServletResponse response) throws TransformerFactoryConfigurationError, ParserConfigurationException, TransformerException, IOException {
		response.setContentType("text/xml");
		
		DocumentBuilder docBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		//creating a new instance of a DOM to build a DOM tree.
		Document doc = docBuilder.newDocument();
		
		Element root = doc.createElement("cityplaces");
		doc.appendChild(root);

		Element city = doc.createElement("city");
		city.setTextContent("Sydney");
		root.appendChild(city);
		
		Element list = doc.createElement("places");
		for (String placeName : places) {
			Element place = doc.createElement("place");
			place.setTextContent(placeName);
			
			list.appendChild(place);
		}
		root.appendChild(list);
		
		Transformer transformer = TransformerFactory.newInstance().newTransformer();
		
		// create string from xml tree
		StringWriter sw = new StringWriter();
		StreamResult result = new StreamResult(sw);
		DOMSource source = new DOMSource(doc);
		transformer.transform(source, result);
		String xmlString = sw.toString();
		
		response.getWriter().write(xmlString);
	}
	
	private void csvReply(HttpServletResponse response) throws IOException {
		response.setContentType("text/csv");
		String output = "City,Place\r\n";
		for (String place : places) {
			output = output + "Sydney," + place + "\r\n";
			
		}
	    getServletContext().log(output);
		System.out.println(output);
		response.getWriter().write(output);
	}
	
	private void jsonReply(HttpServletResponse response) throws IOException, JSONException {
		response.setContentType("text/json");
		JSONObject obj = new JSONObject();
		obj.put("city", "Sydney");
		JSONArray jsonPlaces = new JSONArray();
		for (String place : places) {
			jsonPlaces.put(place);
		}
		obj.put("places",jsonPlaces);
		System.out.println(obj.toString());
		response.getWriter().write(obj.toString());
	}
}
