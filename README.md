<a id="readme-top"></a>

<br />
<div align="center">
  <h3 align="center">Case Study - Credit Module</h3>

  <p align="center">
    A comprehensive Loan API
    <br />
</div>

## About The Project

This project was created within the scope of the case study and covers all functionalities except bonus 2.

### How to Run?
* The packaging type for deploy of project is "war"
* You can easily deploy to tomcat server.
* If you want to deploy project to Bamboo, Jenkins etc. you should maven clean install on your pipes because of this project uses Lombok etc. libraries.
* There is no need for an additional run configuration / VM option. You can start it as a spring boot application via the CreditApplication class.

### How to Use?
* This project using basic Spring authentication and authorization methods.
* You can easily deploy to tomcat server.
* If you want to deploy project to Bamboo, Jenkins etc. you should maven clean install on your pipes because of this project uses Lombok etc. libraries.

### Testing
* You can find / add in memory users (admin / user role based) in SecurityConfig file.
* Some project settings are stored as dynamically. You can see them at import.sql
* At this project, each user can perform operations on their own behalf. The codes are written according to this structure.
* You can test endpoints on Postman. You can find postman collection on Github.

<p align="right">(<a href="#readme-top">back to top</a>)</p>

## application.properties

Before run project, you should some properties according to yourself.

* Example: spring.datasource.url => This path changes according to OS;
  ```sh
  spring.datasource.url=jdbc:h2:file:C:/ING/ingDB
  ```

* Example: spring.jpa.hibernate.ddl-auto => You chan change this property according to your own testing needs.
  ```sh
  spring.jpa.hibernate.ddl-auto=update
  ```

* Example: H2 Console => You chan change this property according to your own testing needs.
  ```sh
  spring.h2.console.enabled=false
  ```

<!-- LICENSE -->
## License

Distributed under the MIT License. See `LICENSE.txt` for more information.

<p align="right">(<a href="#readme-top">back to top</a>)</p>


<!-- CONTACT -->
## Contact

Metin Doğan Çelik - [Metin Doğan Çelik on Linkedin](https://linkedin.com/in/metindogancelik)

<p align="right">(<a href="#readme-top">back to top</a>)</p>