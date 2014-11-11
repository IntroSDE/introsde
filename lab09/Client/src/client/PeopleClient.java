package client;

import java.util.List;

import introsde.document.ws.PeopleService;
import introsde.document.ws.People;
import introsde.document.ws.Person;
 
public class PeopleClient{
	public static void main(String[] args) throws Exception {
		PeopleService service = new PeopleService();
        People people = service.getPeopleImplPort();
        Person p = people.readPerson(1);
        List<Person> pList = people.getPeopleList();
        System.out.println("Result ==> "+p);
        System.out.println("Result ==> "+pList);
        System.out.println("First Person in the list ==> "+pList.get(0).getName());
    }
}