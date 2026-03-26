# Employee Management System

A web-based Employee Management System built using Java Servlets, JSP, JDBC, MySQL, and Apache Tomcat.

## Features
- Role-based login system (Admin and User)
- Add, view, edit, and delete employee records
- Dashboard with employee statistics
- Pagination for employee listing
- Session-based authentication and authorization
- Backend protection for admin-only actions
- Duplicate prevention for email and phone number
- Improved responsive UI with dashboard cards and forms

## Tech Stack
- Java
- Servlets
- JSP
- JDBC
- MySQL
- Apache Tomcat
- HTML
- CSS

## Project Modules
- Login and Logout
- Employee CRUD Operations
- Role-Based Access Control
- Dashboard Summary
- Validation and Exception Handling
- Duplicate Check for Email and Phone

## Folder Structure
```text
src/
 └── main/
      ├── java/
      │    ├── controller/
      │    ├── model/
      │    └── util/
      └── webapp/
           ├── css/
           ├── images/
           ├── WEB-INF/
           └── *.jsp


## How to Run
1. Import the project into Eclipse
2. Configure Apache Tomcat
3. Create the MySQL database and required tables
4. Update database username and password in `DBConnection.java`
5. Run the project on Tomcat
6. Open the application in the browser

## Roles

### Admin
- Add employee
- Edit employee
- Delete employee
- View employees
- Access dashboard

### User
- View dashboard
- View employees only

## Screenshots

### Login Page
(Add screenshot here)

### Dashboard
(Add screenshot here)

### Employee List
(Add screenshot here)

### Add Employee
(Add screenshot here)

## Author
Muhammad Afzal
