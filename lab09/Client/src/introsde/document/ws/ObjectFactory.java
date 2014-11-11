
package introsde.document.ws;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the introsde.document.ws package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _DeletePersonResponse_QNAME = new QName("http://ws.document.introsde/", "deletePersonResponse");
    private final static QName _UpdatePersonResponse_QNAME = new QName("http://ws.document.introsde/", "updatePersonResponse");
    private final static QName _UpdatePersonHealthProfile_QNAME = new QName("http://ws.document.introsde/", "updatePersonHealthProfile");
    private final static QName _ReadPerson_QNAME = new QName("http://ws.document.introsde/", "readPerson");
    private final static QName _MeasureDefinition_QNAME = new QName("http://ws.document.introsde/", "measureDefinition");
    private final static QName _CreatePerson_QNAME = new QName("http://ws.document.introsde/", "createPerson");
    private final static QName _CreatePersonResponse_QNAME = new QName("http://ws.document.introsde/", "createPersonResponse");
    private final static QName _GetPeopleListResponse_QNAME = new QName("http://ws.document.introsde/", "getPeopleListResponse");
    private final static QName _ReadPersonResponse_QNAME = new QName("http://ws.document.introsde/", "readPersonResponse");
    private final static QName _DeletePerson_QNAME = new QName("http://ws.document.introsde/", "deletePerson");
    private final static QName _Person_QNAME = new QName("http://ws.document.introsde/", "person");
    private final static QName _Measure_QNAME = new QName("http://ws.document.introsde/", "Measure");
    private final static QName _MeasureDefaultRange_QNAME = new QName("http://ws.document.introsde/", "measureDefaultRange");
    private final static QName _UpdatePerson_QNAME = new QName("http://ws.document.introsde/", "updatePerson");
    private final static QName _GetPeopleList_QNAME = new QName("http://ws.document.introsde/", "getPeopleList");
    private final static QName _UpdatePersonHealthProfileResponse_QNAME = new QName("http://ws.document.introsde/", "updatePersonHealthProfileResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: introsde.document.ws
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Person }
     * 
     */
    public Person createPerson() {
        return new Person();
    }

    /**
     * Create an instance of {@link DeletePerson }
     * 
     */
    public DeletePerson createDeletePerson() {
        return new DeletePerson();
    }

    /**
     * Create an instance of {@link GetPeopleListResponse }
     * 
     */
    public GetPeopleListResponse createGetPeopleListResponse() {
        return new GetPeopleListResponse();
    }

    /**
     * Create an instance of {@link ReadPersonResponse }
     * 
     */
    public ReadPersonResponse createReadPersonResponse() {
        return new ReadPersonResponse();
    }

    /**
     * Create an instance of {@link UpdatePersonHealthProfileResponse }
     * 
     */
    public UpdatePersonHealthProfileResponse createUpdatePersonHealthProfileResponse() {
        return new UpdatePersonHealthProfileResponse();
    }

    /**
     * Create an instance of {@link LifeStatus }
     * 
     */
    public LifeStatus createLifeStatus() {
        return new LifeStatus();
    }

    /**
     * Create an instance of {@link MeasureDefaultRange }
     * 
     */
    public MeasureDefaultRange createMeasureDefaultRange() {
        return new MeasureDefaultRange();
    }

    /**
     * Create an instance of {@link UpdatePerson }
     * 
     */
    public UpdatePerson createUpdatePerson() {
        return new UpdatePerson();
    }

    /**
     * Create an instance of {@link GetPeopleList }
     * 
     */
    public GetPeopleList createGetPeopleList() {
        return new GetPeopleList();
    }

    /**
     * Create an instance of {@link UpdatePersonHealthProfile }
     * 
     */
    public UpdatePersonHealthProfile createUpdatePersonHealthProfile() {
        return new UpdatePersonHealthProfile();
    }

    /**
     * Create an instance of {@link ReadPerson }
     * 
     */
    public ReadPerson createReadPerson() {
        return new ReadPerson();
    }

    /**
     * Create an instance of {@link MeasureDefinition }
     * 
     */
    public MeasureDefinition createMeasureDefinition() {
        return new MeasureDefinition();
    }

    /**
     * Create an instance of {@link UpdatePersonResponse }
     * 
     */
    public UpdatePersonResponse createUpdatePersonResponse() {
        return new UpdatePersonResponse();
    }

    /**
     * Create an instance of {@link DeletePersonResponse }
     * 
     */
    public DeletePersonResponse createDeletePersonResponse() {
        return new DeletePersonResponse();
    }

    /**
     * Create an instance of {@link CreatePerson }
     * 
     */
    public CreatePerson createCreatePerson() {
        return new CreatePerson();
    }

    /**
     * Create an instance of {@link CreatePersonResponse }
     * 
     */
    public CreatePersonResponse createCreatePersonResponse() {
        return new CreatePersonResponse();
    }

    /**
     * Create an instance of {@link Person.Measurements }
     * 
     */
    public Person.Measurements createPersonMeasurements() {
        return new Person.Measurements();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeletePersonResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.document.introsde/", name = "deletePersonResponse")
    public JAXBElement<DeletePersonResponse> createDeletePersonResponse(DeletePersonResponse value) {
        return new JAXBElement<DeletePersonResponse>(_DeletePersonResponse_QNAME, DeletePersonResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdatePersonResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.document.introsde/", name = "updatePersonResponse")
    public JAXBElement<UpdatePersonResponse> createUpdatePersonResponse(UpdatePersonResponse value) {
        return new JAXBElement<UpdatePersonResponse>(_UpdatePersonResponse_QNAME, UpdatePersonResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdatePersonHealthProfile }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.document.introsde/", name = "updatePersonHealthProfile")
    public JAXBElement<UpdatePersonHealthProfile> createUpdatePersonHealthProfile(UpdatePersonHealthProfile value) {
        return new JAXBElement<UpdatePersonHealthProfile>(_UpdatePersonHealthProfile_QNAME, UpdatePersonHealthProfile.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ReadPerson }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.document.introsde/", name = "readPerson")
    public JAXBElement<ReadPerson> createReadPerson(ReadPerson value) {
        return new JAXBElement<ReadPerson>(_ReadPerson_QNAME, ReadPerson.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MeasureDefinition }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.document.introsde/", name = "measureDefinition")
    public JAXBElement<MeasureDefinition> createMeasureDefinition(MeasureDefinition value) {
        return new JAXBElement<MeasureDefinition>(_MeasureDefinition_QNAME, MeasureDefinition.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreatePerson }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.document.introsde/", name = "createPerson")
    public JAXBElement<CreatePerson> createCreatePerson(CreatePerson value) {
        return new JAXBElement<CreatePerson>(_CreatePerson_QNAME, CreatePerson.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CreatePersonResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.document.introsde/", name = "createPersonResponse")
    public JAXBElement<CreatePersonResponse> createCreatePersonResponse(CreatePersonResponse value) {
        return new JAXBElement<CreatePersonResponse>(_CreatePersonResponse_QNAME, CreatePersonResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetPeopleListResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.document.introsde/", name = "getPeopleListResponse")
    public JAXBElement<GetPeopleListResponse> createGetPeopleListResponse(GetPeopleListResponse value) {
        return new JAXBElement<GetPeopleListResponse>(_GetPeopleListResponse_QNAME, GetPeopleListResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ReadPersonResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.document.introsde/", name = "readPersonResponse")
    public JAXBElement<ReadPersonResponse> createReadPersonResponse(ReadPersonResponse value) {
        return new JAXBElement<ReadPersonResponse>(_ReadPersonResponse_QNAME, ReadPersonResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeletePerson }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.document.introsde/", name = "deletePerson")
    public JAXBElement<DeletePerson> createDeletePerson(DeletePerson value) {
        return new JAXBElement<DeletePerson>(_DeletePerson_QNAME, DeletePerson.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Person }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.document.introsde/", name = "person")
    public JAXBElement<Person> createPerson(Person value) {
        return new JAXBElement<Person>(_Person_QNAME, Person.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link LifeStatus }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.document.introsde/", name = "Measure")
    public JAXBElement<LifeStatus> createMeasure(LifeStatus value) {
        return new JAXBElement<LifeStatus>(_Measure_QNAME, LifeStatus.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MeasureDefaultRange }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.document.introsde/", name = "measureDefaultRange")
    public JAXBElement<MeasureDefaultRange> createMeasureDefaultRange(MeasureDefaultRange value) {
        return new JAXBElement<MeasureDefaultRange>(_MeasureDefaultRange_QNAME, MeasureDefaultRange.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdatePerson }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.document.introsde/", name = "updatePerson")
    public JAXBElement<UpdatePerson> createUpdatePerson(UpdatePerson value) {
        return new JAXBElement<UpdatePerson>(_UpdatePerson_QNAME, UpdatePerson.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetPeopleList }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.document.introsde/", name = "getPeopleList")
    public JAXBElement<GetPeopleList> createGetPeopleList(GetPeopleList value) {
        return new JAXBElement<GetPeopleList>(_GetPeopleList_QNAME, GetPeopleList.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdatePersonHealthProfileResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.document.introsde/", name = "updatePersonHealthProfileResponse")
    public JAXBElement<UpdatePersonHealthProfileResponse> createUpdatePersonHealthProfileResponse(UpdatePersonHealthProfileResponse value) {
        return new JAXBElement<UpdatePersonHealthProfileResponse>(_UpdatePersonHealthProfileResponse_QNAME, UpdatePersonHealthProfileResponse.class, null, value);
    }

}
