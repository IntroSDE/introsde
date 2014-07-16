# LAB03: XML & XPATH


**Introduction to Service Design and Engineering | University of Trento | [Webpage](https://sites.google.com/site/introsdeunitn/lab-sessions/lab-session-3 "Permalink to LAB03: XML and XPATH")**

How do services communicate? What is their language (or 'one' of their languages)?. In this session, we will take a look at one of the languages that power the SOA world: XML. We will learn the basis of it and see how to navigate through it with Java and XPATH. 

## Slides &amp; Code

Links: [PPT slides][1] | [PDF slides][2] | [Source code][3]

## Guiding Notes

The guiding notes below are a summarized version of what is already on the slides.&nbsp;

* Start by pulling updates from the github repository. In your terminals, execute the following: 
```
$ git fetch upstream   $ git merge upstream/master
```

### XML and JSON Intro (15 min)

* Start by opening the *books.xml* file in the Example/Books-XPATH folder of this session   
* Then open *books.json*
* Both present the same information in two formats that are widely used in services. 
* JSON is more widely used in RESTful services. 
* XML is more widely used in SOAP services. 
* They are both used to represent *messages* that services exchange. Today's focus will be in XML, the first of these two. 
* XML stands for *Extensible Markup Language*. 
* Its purpose is to provide a language for encoding documents in a format that is both *human* and *machine* readable
* For a quick explanation of the main parts of XML, make sure you look at the slides. 
* **How can a program automatically read information that is inside an XML?**. As usual, there are many ways. The important thing to know in most cases is that XML can be represented by **Document Tree**, which can be easily navigated. 

### XPATH Examples (30 min)

* XPATH is one of the ways for reading a XML document tree 
* XPath is a *query language* that uses *path expressions* to navigate the XML a query information inside them. 
* To learn XPATH by doing, open this [online evaluator][4] and try on it each of the examples listed below. 
* Copy the content of *books.xml* (in the Example folder) and paste it in the ���Xml Field���
* Let's start with this example to get the node *publisher* from the first *book*

      ```
      /bookstore/book[1]/publisher
      ```
* **Absolute paths** start from the top of the document and begin with /   * A path can select more than one element
  * / by itself means the whole document
  * A path without / in the beginning is a path that starts from the current element
  * Example (to try on the website):

    ``` 
    /bookstore/book/title
    ```

* **Descendant path** is a path that begins with // an can start from anywhere
    * For example, try to selects all the title elements in the document

    ```
    //title
    ``` 

* **Predicates** are used to find specific nodes, or nodes that contains specific values using [] (also known as filters)
    * Examples
    
    ```
    /bookstore/book[1]
    /bookstore/book[last()]
    /bookstore/book[position()<3]
    /bookstore/book[price>6]
    ``` 
    
* **Attributes** can also be selected by themselves or to find elements that have a certain attribute value by using *@* inside predicates
    * Examples: 
    
    ```
    //@year    //book[@year>2000]
    ```
    
* **Node tests** or wildcards are operations performed on nodes to filter them out
    * "*" matches all elements in a level
    * node() matches all nodes
    * text() matches all text nodes
    * Examples:

    ```     //*    /bookstore/*    //@*    node()    //text()
    ```

* More than one path can be selected with the | operator
    * Examples:

    ```    //@year | //book[@year>2000]
   ``` 
    
* An **Axis** is a set of nodes relative to a given node. 
    * X::Y means ���choose Y from the X axis���
    * Examples:
    
    ```     //child::book    //child::text()    //child::node()    //ancestor::book    ```
    
### XPATH and Java (15 min)

* Create a Java project in eclipse opening the **Books-XPATH** folder
* Explore the **XPathTest.java** and the **XPathTestAdvance.java** examples
* Run the programs, add some of the queries in the previous examples and then go for the exercise

### XPATH and Java Exercise (30 min)

* Remember, put your code in your local workspace ('myworkspace'), just to avoid future conflicts if things change in the official repository. For this exercise, create a copy of the HealthProfileExample as a starting point. 
* Make a copy of the HealthProfileReader that replaces the HashMap for people.xml and using Xpath implement:
    * Methods like getWeight and getHeight given a person���s name and lastname
    * A method that accepts name as parameter and prints persons HealthProfile
    * A method that accepts weight and an operator (=, > , <) as parameters and prints people that fulfill that condition.

## Additional notes

### Try XPATH to scrape content from a website 
* Get [scraper][7] (chrome only extension)
* Go to meteotrentino.it
* Righclick on top of one of the temperatures and click "Scrape Similar���, which scrapes the content on the page using xpath (the HTML document it is also treated like a DOM tree)


### Other suggested resources

* [XPATH reference][5]
* [XML editor with a grid view that shows you XPATH of each node][6]


[1]: https://drive.google.com/file/d/0B7ShzcEnCJFNeFdGekFlX2xFb3M/edit?usp=sharing
[2]: https://github.com/cdparra/introsde/tree/master/lab03/Examples/
[3]: https://drive.google.com/file/d/0B7ShzcEnCJFNaXF4a0VwaF9jUkE/edit?usp=sharing
[4]: http://xmltoolbox.appspot.com/xpathevaluator.html 
[5]: http://www.stylusstudio.com/docs/v62/d_xpath15.html
[6]: http://xmlgrid.net/ 
[7]: https://chrome.google.com/webstore/detail/scraper/mbigbapnjcgaffohmbkdlecaccepngjd
