package dozerproject.delegate;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;
import javax.xml.bind.Unmarshaller;

import org.dozer.DozerBeanMapper;

import dozerproject.entity.Person;
import dozerproject.transfer.PersonAnnotationBean;

public class PersonAnnotationBeanDelegate {

	public static PersonAnnotationBean mapFromPerson(Person sourceObject) {
		List<String> myMappingFiles = new ArrayList<String>();
		myMappingFiles.add("dozerMappings.xml");

		DozerBeanMapper mapper = new DozerBeanMapper();
		mapper.setMappingFiles(myMappingFiles);

		System.out.println("Mapping Person to PersonAnnotationBean...");
		return (PersonAnnotationBean) mapper.map(sourceObject, PersonAnnotationBean.class);
	}

	public static Person mapToPerson(PersonAnnotationBean sourceObject) {
		List<String> myMappingFiles = new ArrayList<String>();
		myMappingFiles.add("dozerMappings.xml");

		DozerBeanMapper mapper = new DozerBeanMapper();
		mapper.setMappingFiles(myMappingFiles);

		System.out.println("Mapping PersonAnnotationBean to Person...");
		return (Person) mapper.map(sourceObject, Person.class);
	}

	public static void marshal(String xmlFileName,
			PersonAnnotationBean personBean) {
		File xmlDocument = new File(xmlFileName);
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(PersonAnnotationBean.class);
			Marshaller marshaller = jaxbContext.createMarshaller();
			marshaller.setProperty("jaxb.formatted.output", new Boolean(true));
			marshaller.marshal(personBean, new FileOutputStream(xmlDocument));
		} catch (IOException e) {
			System.out.println(e.toString());
		} catch (PropertyException e) {
			System.out.println(e.toString());
		} catch (JAXBException e) {
			System.out.println(e.toString());
		}
	}
	
	public static PersonAnnotationBean unmarshal(String xmlFileName) {
		File xmlDocument = new File(xmlFileName);
		PersonAnnotationBean personBean = null;
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(PersonAnnotationBean.class);
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			personBean = (PersonAnnotationBean) unmarshaller.unmarshal(xmlDocument);
			
		} catch (PropertyException e) {
			System.out.println(e.toString());
		} catch (JAXBException e) {
			System.out.println(e.toString());
		}
		return personBean;
	}
}
