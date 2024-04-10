
# REST/SOAP Human Resources APIs

Overview:

This API facilitates access to the Human Resource Database, offering users the ability to retrieve information about employees, departments, and other relevant data stored in the schema. This API supports both SOAP and REST interfaces, enabling developers to select the protocol that best suits their requirements.








## Features

- Add employee
- Edit employee
- Get employee Projects
- Get employee manager
- Add a Department
- Get Departments
- Get Projects
- Assign employees to a project
- Request a vacation
- Process vacation : *only managers and HR can process a vacation request*
- Access attendance sheet for an emplyee by date 
- register attendance as an employee




## Documentation

[REST API Documentation](https://documenter.getpostman.com/view/33816455/2sA3BgAFcs)

[SOAP API Documentation](https://documenter.getpostman.com/view/33816455/2sA3BgAvWT)





## Technologies Used

- Java
- Maven
- Jersey
- JAXB (Jakarta XML Binding)
- JAX-WS (Jakarta XML Web Services)
- JUnit
- Project Lombok
- MapStruct
- Jakarta Persistance (Hibernate)
- HikariCP
- Hibernate Validator
- Apache Tomcat


## Run Locally

Clone the project

```bash
  git clone https://github.com/boooTomatoes/HumanResourcesAPI.git
```


Install dependencies


- Create database user and set the username and password values in the persistence.xml.
- Create databse named HumanResources in your MySql Server.
- Run your tomcat apache server and then change the configuration of tomcat in pom.xml.



 Deploy the application using the following maven command.

```bash
    mvn clean package tomcat7:deploy
```

