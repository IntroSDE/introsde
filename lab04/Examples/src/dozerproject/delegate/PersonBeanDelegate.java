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
import dozerproject.transfer.PersonBean;

public class PersonBeanDelegate {

	public static PersonBean mapFromPerson(Person sourceObject) {
		List<String> myMappingFiles = new ArrayList<String>();
		myMappingFiles.add("dozerMappings.xml");

		DozerBeanMapper mapper = new DozerBeanMapper();
		mapper.setMappingFiles(myMappingFiles);

		System.out.println("Mapping Person to PersonBean...");
		return (PersonBean) mapper.map(sourceObject, PersonBean.class);
	}

	public static Person mapToPerson(PersonBean sourceObject) {
		
		List<String> myMappingFiles = new ArrayList<String>();
		myMappingFiles.add("dozerMappings.xml");

		DozerBeanMapper mapper = new DozerBeanMapper();
		mapper.setMappingFiles(myMappingFiles);
		
		System.out.println("Mapping PersonBean to Person...");
		return (Person) mapper.map(sourceObject, Person.class);
	}

	
	public static void marshal(String xmlFileName,
			PersonBean personBean) {
		File xmlDocument = new File(xmlFileName);
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(PersonBean.class);
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
	
	public static PersonBean unmarshal(String xmlFileName) {
		File xmlDocument = new File(xmlFileName);
		PersonBean personBean = null;
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(PersonBean.class);
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			personBean = (PersonBean) unmarshaller.unmarshal(xmlDocument);
			
		} catch (PropertyException e) {
			System.out.println(e.toString());
		} catch (JAXBException e) {
			System.out.println(e.toString());
		}
		return personBean;
	}
}
