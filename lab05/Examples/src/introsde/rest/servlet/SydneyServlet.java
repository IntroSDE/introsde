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
		
	/**
	 * 
	 */
	private static final long serialVersionUID = -2584369106699691304L;
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
		System.out.println("Getting request path parameters...");
		String pathInfo = request.getPathInfo();
		System.out.println("--> "+pathInfo);
		System.out.println("Getting request query parameters...");
		String indexParam = request.getParameter("index");
		Integer index = indexParam == null ? null : new Integer(indexParam);
		
		System.out.println("--> "+index+" (before conversion --> "+indexParam+")");
	
		if ("/json".equals(pathInfo)) {
			try {
				jsonReply(response, index);
			} catch (JSONException e) {
				e.printStackTrace();
				errorReply(response, e, new Error("JSON Exception"));
			}
		} else if ("/csv".equals(pathInfo)) {
			csvReply(response, index);
		} else if ("/xml".equals(pathInfo)) {
			try {
				xmlReply(response,index);
			} catch (TransformerFactoryConfigurationError e) {
				errorReply(response, null, e);
				e.printStackTrace();
			} catch (ParserConfigurationException e) {
				errorReply(response, e, null);
				e.printStackTrace();
			} catch (TransformerException e) {
				errorReply(response, e, null);
				e.printStackTrace();
			}
		} else {
			htmlReply(response,index);
		}
	}
	
	private void htmlReply(HttpServletResponse response, Integer index) throws IOException {
		System.out.println("Preparing HTML reply...");
		String html = "<html><head><title>Sydney Places</title></head><body><ol>";
		
		if (index==null) {
			for (String placeName : places) {
				html = html + "<li>"+placeName+"</li>";
			}			
		} else {
			String placeName = places[index];
			html = html + "<li>"+placeName+"</li>";
		}
		html = html + "</ol></body></html>";
		response.setContentType("text/html");
		response.getWriter().write(html);
		System.out.println("--> "+html);
	}

	private void xmlReply(HttpServletResponse response, Integer index) throws TransformerFactoryConfigurationError, ParserConfigurationException, TransformerException, IOException {
		System.out.println("Preparing XML reply...");
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
		
		if (index==null) {
			for (String placeName : places) {
				Element place = doc.createElement("place");
				place.setTextContent(placeName);
				
				list.appendChild(place);
			}		
		} else {
			String placeName = places[index];
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
		System.out.println("--> "+xmlString);
	}
	
	private void csvReply(HttpServletResponse response, Integer index) throws IOException {
		System.out.println("Preparing CSV reply...");
		response.setContentType("text/csv");
		String output = "City,Place\r\n";
		
		if (index==null) {
			for (String placeName : places) {
				output = output + "Sydney," + placeName + "\r\n";	
			}	
		} else {
			String placeName = places[index];
			output = output + "Sydney," + placeName + "\r\n";	
		}
		
		getServletContext().log(output);
		System.out.println(output);
		response.getWriter().write(output);
		System.out.println("--> "+output);
	}
	
	private void jsonReply(HttpServletResponse response, Integer index) throws IOException, JSONException {
		System.out.println("Preparing JSON reply...");
		response.setContentType("text/json");
		
		JSONObject obj = new JSONObject();
		obj.put("city", "Sydney");
		JSONArray jsonPlaces = new JSONArray();
		
		if (index==null) {
			for (String placeName : places) {
				jsonPlaces.put(placeName);
			}
		} else {
			String placeName = places[index];
			jsonPlaces.put(placeName);
		}
		
		obj.put("places",jsonPlaces);
		response.getWriter().write(obj.toString());
		System.out.println("--> "+obj.toString());
	}
	
	private void errorReply(HttpServletResponse response, Exception e, Error err) throws IOException, JSONException {
		String errorMsg = err == null ? e.getMessage() : err.getMessage();
		response.sendError(500, errorMsg);;
	}
}
