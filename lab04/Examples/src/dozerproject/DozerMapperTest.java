package dozerproject;


import dozerproject.delegate.PersonAnnotationBeanDelegate;
import dozerproject.delegate.PersonBeanDelegate;
import dozerproject.entity.Person;
import dozerproject.transfer.PersonBean;
import dozerproject.transfer.PersonAnnotationBean;

import org.joda.time.DateTime;

public class DozerMapperTest {
    
    public static void main (String argus[]){        
        System.out.println("Creating a test Person...");
        Person p = new Person();
        p.setFirstName("Cristhian");
        p.setLastName("Parra");
        p.setAddress("Povo Trento");
        p.setDbID("1234");
        p.setBirthdate(new DateTime(1984, 6, 21, 18, 0, 0, 0));
        System.out.println("--> Person = "+p.toString());
        
        System.out.println("Mapping the test person to the transfer beans...");
        PersonBean pb = PersonBeanDelegate.mapFromPerson(p);
        PersonAnnotationBean pab = PersonAnnotationBeanDelegate.mapFromPerson(p);
        System.out.println("--> PersonBean = "+pb.toString());
        System.out.println("--> PersonAnnotationBean = "+pab.toString());
        

        System.out.println();
        System.out.println("Marshalling transfer beans to...");
        String personBeanXML = "personBean.xml";
        String personAnnotationBeanXML = "personAnnotationBean.xml";
        System.out.println("--> "+personBeanXML);
        System.out.println("--> "+personAnnotationBeanXML);
        
        PersonBeanDelegate.marshal(personBeanXML, pb);
        PersonAnnotationBeanDelegate.marshal(personAnnotationBeanXML, pab);
       
        System.out.println();
        System.out.println("Un-Marshalling from created XMLs...");
        PersonBean pbFromFile = PersonBeanDelegate.unmarshal(personBeanXML);
        PersonAnnotationBean pabFromFile  = PersonAnnotationBeanDelegate.unmarshal(personAnnotationBeanXML);
        System.out.println("--> Unmarshalled PersonBean = "+pbFromFile.toString());
        System.out.println("--> Unmarshalled PersonAnnotationBean = "+pabFromFile.toString());

        System.out.println();
        System.out.println("Mapping to entity...");
        Person p1 = PersonBeanDelegate.mapToPerson(pbFromFile);
        Person p2 = PersonAnnotationBeanDelegate.mapToPerson(pabFromFile);
        System.out.println("--> Person from PersonBean = "+p1.toString());
        System.out.println("--> Person from PersonAnnotationBean = "+p2.toString());
    }
}
