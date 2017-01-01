# Rakuten Products Management Solution
Rest API for managing products &amp; categories in a secured context

## Guide

## Prerequisites
- JDK 1.8 or later
- Maven 3 or later
- MySQL 5.6 or later

## Stack
- Spring MVC
- Spring Security
- Spring Data
- Maven
- MySQL

## Run
Deploy on any web server, run https://hostName:8443/rakuten-ecommerce/swagger-ui.html

## Architecture & High-level design

###Layers:
**Security layer:**  
Basic authentication technique is used by the support of Spring Security. 
Security configuration are made dynamic to sustain any number of users/roles to be added in runtime, while only application-restart is needed to reload the security configurations. 
Also, it worth to mention that, a default -yet configurable- super admin is created on the system startup for the sake of "ease of testing". Still, such behavior can be disabled via the boolean flag configuration: `application.ecommerce.security.create_default_super_user`. 
 
**Services layer:**  
- It's the exposure layer for the backend business logic, such exposure is achieved via Spring rest services & documented via "Swagger".
- This layer contains validations for the DTOs applied utilizing Java validation mechanism with hibernate-validator as the implementation.
- Also this layer is responsible for transforming the DTO models to the business model before sending them to the business layer. Such transformation is achieved by the "ModelMapper" library. 
-Design for this layer is made in a generic way, supporting by default the CRUD rest API endpoints & their calls to the business layer, which is designed in the same generic way.  


**Business layer:**
- layer encapsulating the business logic of the application (managers), it's purely Java code, utilizing Java validations & transaction management provided by spring. 
- After finishing the needed business logic, this layer delegates persisting the business model objects to the Repository layer, which uses Spring Data repositories. 
- Business manager layer, as well as the exposure service layer, is designed in a generic way, handling basic operations for the CRUD basic operations, utilizing the out-of-the-box CRUD implementation provided by Spring Data Repositories.  
- Business layer is responsible for checking/validating different business cases before delegating to the persistence repositories. Also throwing business exceptions in case inputs are not valid or conflicting with current saved data. Managers are not responsible for handling or logging exceptions, rather; such responsibility is delegated to a global handler controller advice for the sake of "separation of concerns".
  
**Data Access layer (DAL):**
Simply, Spring data repositories are used. They also can be replaced with any other repository implementation that provides persisting capabilities to business domain POJOs.  

**Exception Handling:**
As aforementioned, exception handling & logging is delegated to "GlobalExceptionHandler" controller advice, which checks for the caught exceptions, transform them into the appropriate response message to the caller, with the corresponding HTTP status. 

###Important Business Architecture & Design Decisions/Trad-offs :
**Tree Model** 
Since product categories should be represented in a hierarchy, so it's clear that we need a recursive persistent relation. Then it's a tree!
The best choice would be [Nested Set Model] (https://en.wikipedia.org/wiki/Nested_set_model) since nested set is really efficient when it comes to get path to root, get path from root to child node, check what children are belonging to the grand parent, & so forth. 
Nevertheless, implementation for such model with handling to edge cases & testing, would may not fit into the time constraint, so I've gone for a straight forward implementation, only parent to children & child back to parent paths are maintained.

 **Security**
 It wasn't a big deal to choose the basic authentication mechanism over other potentials, such as OAuth2 for example, since we don't need such complication here. So "Basic Authentication" with https would be fine. 
 The trad-off was regarding to make it static vs dynamic configurations for the roles. I've chosen the later option to make the url patterns provided dynamically. pros are to make role & endpoint securing is a dynamic process, cons are related to the validity of the URLs given in the access pattern field, validation may be ensured via maintaining a registry/hashtable of the whole URLs of the endpoints exist in the application, but again to keep it simple, I've put assumption that configuration should be a correct endpoint!   

