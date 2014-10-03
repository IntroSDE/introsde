package bookstore;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;

public class JavaToXML {
	public void marshalXMLDocument(File xmlDocument) {
		try {

			JAXBContext jaxbContext = JAXBContext.newInstance(Catalog.class);

			Marshaller marshaller = jaxbContext.createMarshaller();

			marshaller.setProperty("jaxb.formatted.output", new Boolean(true));

			Catalog catalog = new Catalog();

			catalog.setJournal("Java Technology");
			catalog.setPublisher("UNITN LAB developerWorks");

			catalog.setEdition("OCT-2010");
			catalog.setTitle("Managing XML data: Tag URIs");
			catalog.setAuthor("Elliotte Harold");

			marshaller.marshal(catalog, new FileOutputStream(xmlDocument));

		} catch (IOException e) {
			System.out.println(e.toString());

		} catch (PropertyException e) {
			System.out.println(e.toString());

		} catch (JAXBException e) {
			System.out.println(e.toString());

		}

	}

	public static void main(String[] argv) {
		String xmlDocument = "catalog.xml";
		JavaToXML javaToXML = new JavaToXML();
		javaToXML.marshalXMLDocument(new File(xmlDocument));
	}
}
