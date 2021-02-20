# bookstore_sample
It is a simple bookstore application to demonstrate CRUD operation in spring boot.

---
# Functionality And Approach Taken:
Using bookstore app you can 1)store book, 2)get book by Id 3)get all books 4)update book and 5)delete book
BookStore app uses PostgreSQL as the relational database. Jpa/Hibernate is used to communicate with database.

<li>Addition of book: Addition of book is done by POST “/api/book”: If the book is not registered before, it should be registered, and the amount of books to be added can be specified.</li>
<li>Get book by Id: It gets all the detail of book for that id.  The end point for this function is GET ”/api/book?id=”. Here id is the request param.</li>
<li>Get All Books: It gets all the books that are registered. The url for this function is: “/api/allBooks”.</li>
<li>Update a book: The end point is PUT “/api/book”. Here we pass the BookReqDto object too. While updating if “bookId” in the BookDto is not existing in database then it gives exception. While updating  if book with that bookId is present then it updates the required field other than bookId.</li>
<li>Delete a Book: The end point is DELETE “/api/book?id=”. Here the id is request param. It will delete one book of given id at a time. If there is no book to delete, it will throw exception.</li>
<li>Unit Tests: The unit test is done at all the package level. In Controller level, the unit test is done using mockMvc, Mockito and junit. In Service level, the unit test is done using junit.  It has over 100% line code coverage. Jacoco can be used to generate unit test report. SonarQube is run in port 9000 to generate visualisation of thee report.</li>


---
# Tools/Framework
<ul>
<li>PostgreSQL: PostgreSQL is a open-source relational database management system.</li>
<li>JPA: Java Persistence API(JPA) is a Java programming interface specification that describes the management of relational data in applications using Java Platform.</li>
<li>JUNIT/Mockito: Junit is the unit testing framework for the Java programming language. Mockito is a mocking framework.</li>
<li>Jacoco: Jacoco is a Java Code Coverage tool. The jacoco maven plugin is used in this project. This generate unit test code coverage report.</li>
<li>Lombok: Project Lombok is a java library that automatically plugs into editor and build tools, spicing up java. Getter, Setters, Constructors can be created with annotation without writing the code with the help of Lombok.</li>
<li>ModelMapper: ModelMapper is a simple, intelligent, object mapping tool. It is used to map the object in this project.</li>
<li>OpenAPI: OpenAPI is open-source framework that helps developers design, build, document and consume RESTful Web services.</li>
</ul>

---
# Steps To Run The Application
<ul>
<li>This project uses project Lombok. So, if you do not have Lombok plugins inserted into your IDE, please install it into your IDD.</li>
<li>Install PostgreSQL in your laptop if you do not have one. You can download it from here: https://www.digitalocean.com/community/tutorials/how-to-install-and-use-postgresql-on-ubuntu-18-04 </li>
<li>Now in your IDE, open the application.properties file, change the spring datasource url, username and password. Right now the spring datasource url=”jdbc:mysql://localhost:3306/bookstore” . If not please put on your datasource url and put on your database username and password in these fields.</li>
<li>Now you can run your application. The table “book” will be automatically created in “bookstore”. By default, it runs in port:9999. You can open up the swagger ui from  following url: http://localhost:9999/swagger-ui.html .</li>
<li>Once you have set up your database, you can also run the unit tests.</li>
<li>You can also use jacoco plugins to generate unit test reports. For that go to the IDE gradle -> verification -> double click test , gradle -> verification -> double click on jacocoTestReport and gradle -> other -> double click on sonarqube to visualise the report on sonarQube "localhost:9000"</li>
<li>To run the application gradle ->Tasks ->application ->bootRun and you can access all the api using the localhost url "http://localhost:8000/" </li>
</ul>


