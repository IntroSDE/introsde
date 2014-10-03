package bookstore;

import bookstore.generated.*;

import javax.xml.bind.*;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Schema;

import org.xml.sax.SAXException;

import java.io.*;
import java.util.List;

public class JAXBUnMarshaller {
	public void unMarshall(File xmlDocument) {
		try {

			JAXBContext jaxbContext = JAXBContext.newInstance("bookstore.generated");

			Unmarshaller unMarshaller = jaxbContext.createUnmarshaller();
			SchemaFactory schemaFactory = SchemaFactory
					.newInstance("http://www.w3.org/2001/XMLSchema");
			Schema schema = schemaFactory.newSchema(new File(
					"catalog.xsd"));
			unMarshaller.setSchema(schema);
			CustomValidationEventHandler validationEventHandler = new CustomValidationEventHandler();
			unMarshaller.setEventHandler(validationEventHandler);

			@SuppressWarnings("unchecked")
			JAXBElement<CatalogType> catalogElement = (JAXBElement<CatalogType>) unMarshaller
					.unmarshal(xmlDocument);

			CatalogType catalog = catalogElement.getValue();

			System.out.println("Journal Title: " + catalog.getJournalTitle());
			System.out.println("Publisher: " + catalog.getPublisher());

			List<JournalType> journalList = catalog.getJournal();
			for (int i = 0; i < journalList.size(); i++) {

				JournalType journal = (JournalType) journalList.get(i);

				List<ArticleType> articleList = journal.getArticle();
				for (int j = 0; j < articleList.size(); j++) {
					ArticleType article = (ArticleType) articleList.get(j);

					System.out.println("Article Edition: "
							+ article.getEdition());
					System.out.println("Title: " + article.getTitle());
					System.out.println("Author: " + article.getAuthor());

				}
			}
		} catch (JAXBException e) {
			System.out.println(e.toString());
		} catch (SAXException e) {
			System.out.println(e.toString());
		}
	}

	public static void main(String[] argv) {
		File xmlDocument = new File("catalog.xml");
		JAXBUnMarshaller jaxbUnmarshaller = new JAXBUnMarshaller();

		jaxbUnmarshaller.unMarshall(xmlDocument);

	}

	class CustomValidationEventHandler implements ValidationEventHandler {
		public boolean handleEvent(ValidationEvent event) {
			if (event.getSeverity() == ValidationEvent.WARNING) {
				return true;
			}
			if ((event.getSeverity() == ValidationEvent.ERROR)
					|| (event.getSeverity() == ValidationEvent.FATAL_ERROR)) {

				System.out.println("Validation Error:" + event.getMessage());

				ValidationEventLocator locator = event.getLocator();
				System.out.println("at line number:" + locator.getLineNumber());
				System.out.println("Unmarshalling Terminated");
				return false;
			}
			return true;
		}

	}
}
