# LAB03: XML, XPATH & XML Schemas

**Introduction to Service Design and Engineering | University of Trento | [Webpage](https://sites.google.com/site/introsdeunitn/lab-sessions/lab-session-3 "Permalink to LAB03: XML and XPATH")**

How do services communicate? What's the language (or 'one' of their languages) in which messages traverse the realms of SOAs?. In this module, we will give a practical look to the cornerstone language of service-oriented architectures: XML. We will do so by exploring a framework for navigating the content of an xml programmatically.  

## Slides &amp; Code

Links: [PPT slides][1] | [PDF slides][2] | [Source code][3]

## Guiding Notes

The guiding notes below are a summarized version of what is already on the slides.&nbsp;

* Start by pulling updates from the github repository. In your terminals, execute the following: 

    ```sh
    $ git fetch upstream
    $ git merge upstream/master
    ```

### XML and JSON Intro (15 min)
* Start by opening the *books.xml* file in the Example/Books-XPATH folder of this module   
* Then open *books.json*
* Both present the same information in two formats that are widely used in services. 
* JSON is more widely used in RESTful services. 
* XML is more widely used in SOAP services. 
* They are both used to represent *messages* that services exchange. Today's focus will be in XML, the first of these two. 
* XML stands for *Extensible Markup Language*. 
* Its purpose is to provide a language for encoding documents in a format that is both *human* and *machine* readable
* For a quick explanation of the main parts of XML, make sure you look at the slides. 
* **How can a program automatically read information that is inside an XML?**. As usual, there are many ways and the important thing to know in most cases is that XML can be represented by **Document Tree**, which can be easily navigated. 

### XPATH Examples (30 min)
* XPATH is one of the ways for reading an XML document tree 
* XPath is a *query language* that uses *path expressions* to navigate the XML a query information inside them. 
* To learn XPATH by doing, open this [online evaluator][4] and try on it each of the examples listed below. 
* Copy the content of *books.xml* (in the Example folder) and paste it in the "Xml Field"
* Let's start with this example to get the node *publisher* from the first *book*

    ```
    /bookstore/book[1]/publisher
    ```

* **Absolute paths** start from the top of the document and begin with /
    * A path can select more than one element
    * / by itself means the whole document
    * A path without / in the beginning is a path that starts from the current element
    * Example (to try on the website):

    ``` 
    /bookstore/book/title
    ```

* **Descendant path** is a path that begins with // an can start from anywhere. For example, try to select all the title elements in the document

    ```
    //title
    ``` 

* **Predicates** are used to find specific nodes, or nodes that contains specific values using [] (also known as filters). For Example:
   
    ```
    /bookstore/book[1]
    /bookstore/book[last()]
    /bookstore/book[position()<3]
    /bookstore/book[price>6]
    ``` 
    
* **Attributes** can also be selected by themselves or to find elements that have a certain attribute value by using *@* inside predicates. For example:

    ```
    //@year
    //book[@year>2000]
    ```
    
* **Node tests** or wildcards are operations performed on nodes to filter them out
    * "*" matches all elements in a level
    * node() matches all nodes
    * text() matches all text nodes
    * Examples:
    
    ```
    //*
    /bookstore/*
    //@*
    node()
    //text()
    ```

* More than one path can be selected with the | operator as seen in the following example:
    
    ```
    //@year | //book[@year>2000]
    ``` 
    
* An **Axis** is a set of nodes relative to a given node. 
    * X::Y means "choose Y from the X axis"
    * Examples:
    
    ```
    //child::book
    //child::text()
    //child::node()
    //ancestor::book
    ```
    
### XPATH and Java (15 min)
* Create a Java project in eclipse opening the **Books-XPATH** folder
* Explore the **XPathTest.java** and the **XPathTestAdvance.java** examples
* Run the programs, add some of the queries in the previous examples and then go for the exercise

### XPATH and Java Exercise 1 (10 min)
* Remember, put your code in your local workspace ('myworkspace'), just to avoid future conflicts if things change in the official repository. 
* **Exercise 03.01:** create a copy of the **XPathTest.java** as a starting point. Then modify **XPathTest.java** so that it prints out also the name of authors. 

### XPATH and Java Exercise 2 (25 min)
* **Exercise 03.02:** create a copy of the HealthProfileReader example as a starting point.
* Make a copy of the HealthProfileReader that replaces the HashMap for people.xml and using Xpath implement:
    * Methods like **getWeight** and **getHeight** given a person's name and lastname
    * A method that accepts name as parameter and prints persons HealthProfile
    * A method that accepts weight and an operator (=, > , <) as parameters and prints people that fulfils that condition.

### XML Schemas Introduction (25 min)
* An XML schema describes the structure of an XML document
* An XML Schema is written in XML
* It is an XML-based alternative to DTD (document type definition - which is yet another set of markups to learn)
* An XML Schema defines:
    * **elements** and **attributes** that can appear in a document
    * **data types** for elements and attributes
    * which elements are **child** elements
    * the **order** of child elements
    * whether an element is **empty** or can include **text**
    * **default and fixed values** for elements and attributes

### XML Schemas Examples
* Open Example/xml-schemas/Example1.xsd, and explore its content.  
* You will find information on the slides what each part of the XSD file indicates (this will be further explained in detail during [one of the theoretical lectures](https://sites.google.com/site/introsdeunitn/home/introduction-to-xml))

### XSD Examples
* **Exercise 1**
    * Open this [XML/XSD validation tool online][8]
    * Copy the content of the first example Example/xml-schemas/Example1.xml and validate its against its XSD
    * Modify the Example 1 by adding a **Health Profile** as defined in the XSD, then validate it 
* **Exercise 2**
    * Go to the [online validator][8] 
    * Copy the Example 5 xsd in the examples folder into the XSD Schema.
    * Create a valid XML instance of this schema
* Check the other examples and the extended reference at the end of this page for more information


## Additional notes and examples

### Try XPATH to scrape content from a website 
* Get [scraper][7] (chrome only extension)
* Go to meteotrentino.it
* Righclick on top of one of the temperatures and click "Scrape Similar", which scrapes the content on the page using xpath (the HTML document it is also treated like a DOM tree)

### Other suggested resources
* Learn more about XML Schemas in [W3Schools][9] or [here][10]
* [XPATH reference][5]
* [XML editor with a grid view that shows you XPATH of each node][6]
* Other online XML validators 
    * [Decisionsoft schemaValidate][11] 
    * [Utilities-Online][12] 

### More of XML/XSD reference and examples

* XSD most common built-in data types are:
    * xsd:string
    * xsd:decimal
    * xsd:integer
    * xsd:boolean
    * xsd:date
    * xsd:time
* The complete built-in data type hierarchy
    * http://www.w3.org/TR/xmlschema-2/#built-in-datatypes
* Complex Data Types
    * A complex element is an XML element that contains other elements and/or attributes. 
    * There are four kinds of complex elements:
        * empty elements
        * elements that contain only other elements
        * elements that contain only text (and attributes)
        * elements that contain both other elements and text
* Empty Elements (See XML example 1 + corresponding XSD)

    ```xml 
        <person id="12345">
        </person>
    ```

    ```xml 
        <xsd:element name="person" type="personType"/>
        <xsd:complexType name="personType">c
        </xsd:complexType>
    ```

* Elements that contain only Elements (See XML example 1 + corresponding XSD) 
    
    ```xml 
        <person>
            <firstName>George R. R.</firstName>
            <lastName>Martin</lastName>
            <birthDate>1970-06-21</birthDate>
        </person>
    ```

    ```xml 
        <xsd:element name="person" type="personType"/>
        <xsd:complexType name="personType">
        <xsd:sequence>
            <xsd:element name="firstName" type="xsd:string"/>
            <xsd:element name="lastName"  type="xsd:string"/>
            <xsd:element name="birthDate" type="xsd:date"/>
         </xsd:sequence>
        </xsd:complexType>  
    ```

* Elements that contain only Text and Attributes (See XML example 2 + corresponding XSD)

* XML Elements

    ```xml 
        <shoesize country="france">35</shoesize>
    ```

    ```xml 
        <xsd:element name="shoesize" type="shoesizeType"/>
        <xsd:complexType name="shoesizeType">
            <xsd:simpleContent>
                <xsd:extension base="xsd:integer">
                    <xsd:attribute name="country" type="xsd:string"/>
                </xsd:extension>
            </xsd:simpleContent>
        </xsd:complexType>
    ```

* Elements that contain both elements and text (See XML example 3 + corresponding XSD)

    ```xml
    <letter>
        Dear Mr.<name>John Smith</name>.
        Your order <orderid>1032</orderid>
        will be shipped on <shipdate>2001-07-13</shipdate>.
    </letter>
    ```

    ```xml 
    <xsd:schema xmlns:xsd="http://www.w3.org/2001/XMLSchema">
        <xsd:element name="letter">
            <xsd:complexType mixed="true">
                <xsd:sequence>
                    <xsd:element name="name" type="xsd:string"/>
                    <xsd:element name="orderid" type="xsd:positiveInteger"/>
                    <xsd:element name="shipdate" type="xsd:date"/>
                </xsd:sequence> 
            </xsd:complexType>
        </xsd:element>  
    </xsd:schema>
    ```
    
* **XSD Indicators:**
    * **Order indicators** are used to define the order of the elements
        * all, choice, sequence 
    * **Occurrence indicators** are used to define how often an element can occur
        * maxOccurs, minOccurs
    * **Group indicators** are used to define related sets of elements.
        * group name, attributeGroup name

* Group names (See XML example 4 + corresponding XSD)

    ```xml 
    <xsd:group name="persongroup">
        <xsd:sequence>
            <xsd:element name="firstName" type="xsd:string"/>
            <xsd:element name="lastName"  type="xsd:string"/>
            <xsd:element name="birthDate" type="xsd:date"/>
         </xsd:sequence>
    </xsd:group>
    <xsd:element name="person" type="personinfo"/>
    <xsd:complexType name="personinfo" >
        <xsd:sequence>
            <xsd:group ref="persongroup"/>
            <xsd:element name="country" type="xsd:string"/>
        </xsd:sequence>
    </xsd:complexType>
    ```

* AttributeGroup name (See XML example 5 + corresponding XSD)

    ```xml 
    <xsd:attributeGroup name="elementAttrGroup">
        <xsd:attribute name="refURI" type="xsd:anyURI" use="optional">
        </xsd:attribute>
        <xsd:attribute name="id" type="xsd:integer">
        </xsd:attribute>
    </xsd:attributeGroup>
    <xsd:complexType name="SomeEntity" >
        <xsd:simpleContent>
            <xsd:extension base="xsd:string">
                <xsd:attributeGroup ref="elementAttrGroup"/>
            </xsd:extension>
         </xsd:simpleContent>
    </xsd:complexType>
    <xsd:element name="root" type="SomeEntity"/>
    ```

* Sustitution

    ```xml 
    <xs:element name="name" type="xs:string"/>
    <xs:element name="navn" substitutionGroup="name"/>
    ```

    ```xml
    <xs:element name="name" type="xs:string"/>
    <xs:element name="navn" substitutionGroup="name"/>
    <xs:complexType name="custinfo">
        <xs:sequence>
            <xs:element ref="name"/>
        </xs:sequence>
    </xs:complexType>
    <xs:element name="customer" type="custinfo"/>
    <xs:element name="kunde" substitutionGroup="customer"/>
```

* Valid XMLs

    ```xml
    <customer>
        <name>John Smith</name> 
    </customer>
    ```

    ```xml
        <kunde>
            <navn>John Smith</navn>
        </kunde>
    ```


[1]: https://drive.google.com/file/d/0B7ShzcEnCJFNVi1LWERhbVFoQ3c/edit?usp=sharing
[2]: https://github.com/cdparra/introsde/tree/master/lab02/Example/
[3]: https://drive.google.com/file/d/0B7ShzcEnCJFNNWttdGRvZmpIZUE/edit?usp=sharing
[4]: http://xmltoolbox.appspot.com/xpathevaluator.html 
[5]: http://www.stylusstudio.com/docs/v62/d_xpath15.html
[6]: http://xmlgrid.net/ 
[7]: https://chrome.google.com/webstore/detail/scraper/mbigbapnjcgaffohmbkdlecaccepngjd
[8]: http://www.utilities-online.info/xsdvalidation/#.Ul0rkGRvj40
[9]: http://www.w3schools.com/schema/default.asp
[10]: http://www.xfront.com/files/xml-schema.html
[11]: http://tools.decisionsoft.com/schemaValidate/
[12]: http://www.utilities-online.info/xsdvalidation/#.Ul0rkGRvj40
