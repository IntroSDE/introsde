package bookstore;

import bookstore.generated.*;

import javax.xml.bind.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class JAXBMarshaller {
	public void generateXMLDocument(File xmlDocument) {
		try {

			JAXBContext jaxbContext = JAXBContext.newInstance("bookstore.generated");
			Marshaller marshaller = jaxbContext.createMarshaller();
			marshaller.setProperty("jaxb.formatted.output", new Boolean(true));
			bookstore.generated.ObjectFactory factory = new bookstore.generated.ObjectFactory();

			CatalogType catalog = factory.createCatalogType();
			catalog.setJournalTitle("Java Technology 2.22");
			catalog.setPublisher("UNITN developerWorks");

			JournalType journal = factory.createJournalType();
			List<JournalType> journalList = catalog.getJournal();
			journalList.add(journal);

			ArticleType article = factory.createArticleType();

			article.setEdition("Oct-2010");
			article.setTitle("Managing XML data: Tag URIs");
			article.setAuthor("Elliotte Harold");

			List<ArticleType> articleList = journal.getArticle();
			articleList.add(article);

			article = factory.createArticleType();

			article.setEdition("October-2005");
			article.setTitle("JAXP validation");
			article.setAuthor("Brett McLaughlin");

			articleList = journal.getArticle();
			articleList.add(article);

			JAXBElement<CatalogType> catalogElement = factory
					.createCatalog(catalog);
			marshaller.marshal(catalogElement,
					new FileOutputStream(xmlDocument));

		} catch (IOException e) {
			System.out.println(e.toString());

		} catch (JAXBException e) {
			System.out.println(e.toString());

		}

	}

	public static void main(String[] argv) {
		String xmlDocument = "catalog.xml";
		JAXBMarshaller jaxbMarshaller = new JAXBMarshaller();
		jaxbMarshaller.generateXMLDocument(new File(xmlDocument));
	}
}
