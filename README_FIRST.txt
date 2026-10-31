QUESTION 2 — SPRING BOOT EMPLOYEE MANAGEMENT REST API
=======================================================

This project answers Question 2 from the MSE-2 sample paper.
No MySQL or other database is used. Employee objects are stored in an
ArrayList while the application is running. Data resets when the app stops.

REQUIREMENTS
------------
- JDK 17 or newer
- IntelliJ IDEA
- Internet connection on first run so Maven can download dependencies
- Postman (recommended for testing; optional)

PROJECT FILES
-------------
pom.xml
  Adds Spring Boot Web dependency and configures the project.

EmployeeApiApplication.java
  Main class that starts Spring Boot.

Employee.java
  Model class containing id, name, department and salary,
  constructors, getters and setters.

EmployeeController.java
  REST endpoints for adding, viewing, updating and deleting employees.

application.properties
  Sets server port to 8080.

HOW TO RUN IN INTELLIJ
----------------------
1. Extract this ZIP.
2. Open IntelliJ IDEA.
3. Select File -> Open.
4. Select the extracted Question2_SpringBoot_Ready_To_Go folder
   (the folder containing pom.xml).
5. If IntelliJ asks, trust/open the project and load it as a Maven project.
6. Wait for Maven to download dependencies.
7. Open EmployeeApiApplication.java.
8. Click the green Run icon beside main().
9. Wait until the console shows that the application has started.
10. The API is now available at http://localhost:8080.

TEST THE FIVE OPERATIONS
------------------------

1) ADD EMPLOYEE
Method: POST
URL: http://localhost:8080/employees
Postman: Body -> raw -> JSON

{
  "id": 1,
  "name": "Aarav Sharma",
  "department": "IT",
  "salary": 55000
}

Expected status: 201 Created
The response contains the employee that was added.

2) GET ALL EMPLOYEES
Method: GET
URL: http://localhost:8080/employees

Expected status: 200 OK
Returns the list of employees.

3) GET EMPLOYEE BY ID
Method: GET
URL: http://localhost:8080/employees/1

Expected status: 200 OK if found.
Expected status: 404 Not Found if the ID does not exist.

4) UPDATE EMPLOYEE
Method: PUT
URL: http://localhost:8080/employees/1
Postman: Body -> raw -> JSON

{
  "id": 1,
  "name": "Aarav Sharma",
  "department": "Development",
  "salary": 60000
}

Expected status: 200 OK if updated.
Expected status: 404 Not Found if the ID does not exist.

5) DELETE EMPLOYEE
Method: DELETE
URL: http://localhost:8080/employees/1

Expected status: 200 OK if deleted.
Expected status: 404 Not Found if the ID does not exist.

IMPORTANT:
Run POST first to add an employee. Then test GET, PUT and DELETE.
If you stop and restart the application, the ArrayList becomes empty.

EXAM-READY EXPLANATION
---------------------
(a) Employee model:
Create an Employee class with private fields id, name, department and salary.
Provide a default constructor, parameterized constructor, getters and setters.

(b) Add employee:
Use @PostMapping to handle an HTTP POST request.
Use @RequestBody to convert incoming JSON into an Employee object.
Add the object to the employee list.

(c) Retrieve employees:
Use @GetMapping to retrieve all employees.
Use @GetMapping("/{id}") with @PathVariable to retrieve one employee by ID.

(d) Update employee:
Use @PutMapping("/{id}") to receive the employee ID from the URL and the
updated employee details from the request body. Find the employee and update
its name, department and salary.

(e) Delete employee:
Use @DeleteMapping("/{id}") to find and remove the employee.
Return HTTP 200 for successful deletion and HTTP 404 if the employee is absent.

ANNOTATIONS / CONCEPTS TO REMEMBER
----------------------------------
@SpringBootApplication : Marks the main Spring Boot application class.
@RestController       : Marks a class as a REST controller.
@RequestMapping       : Defines a common URL path for controller methods.
@PostMapping          : Handles POST requests (create).
@RequestBody          : Converts JSON request body into a Java object.
@GetMapping           : Handles GET requests (read).
@PathVariable         : Reads a value from the URL path, such as employee ID.
@PutMapping           : Handles PUT requests (update).
@DeleteMapping        : Handles DELETE requests (delete).
ResponseEntity        : Controls the HTTP status code and response body.
ArrayList             : Stores employee objects temporarily in memory.

HTTP STATUS CODES USED
----------------------
201 Created       : Employee successfully added.
200 OK            : Request completed successfully.
404 Not Found     : Employee ID does not exist.
409 Conflict      : Employee ID already exists.

For the exam, write the Employee model first, then the controller methods
in the order POST, GET all, GET by ID, PUT and DELETE.
