<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.Employee" %>

<%
if (session.getAttribute("user") == null) {
    response.sendRedirect(request.getContextPath() + "/login.jsp");
    return;
}

String role = (String) session.getAttribute("role");
if (!"admin".equals(role)) {
    response.sendRedirect(request.getContextPath() + "/dashboard");
    return;
}

Employee e = (Employee) request.getAttribute("emp");
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit Employee</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/style.css">
</head>
<body>

<div class="navbar">
    <a href="dashboard">Home</a>
    <a href="addEmployee.jsp">Add Employee</a>
    <a href="viewEmployees" class="active">View Employees</a>
    <a href="logout">Logout</a>
</div>

<div class="container">

    <h2>Edit Employee</h2>
    <p style="text-align:center; color:#64748b; margin-top:-10px; margin-bottom:25px;">
        Update employee details and save the latest information.
    </p>

    <div class="form-panel">
        <form action="updateEmployee" method="post">

            <input type="hidden" name="id" value="<%= e.getId() %>">

            <div class="form-grid">

                <div class="form-group">
                    <label>First Name</label>
                    <input type="text" name="firstName" value="<%= e.getFirstName() %>">
                </div>

                <div class="form-group">
                    <label>Last Name</label>
                    <input type="text" name="lastName" value="<%= e.getLastName() %>">
                </div>

                <div class="form-group">
                    <label>Email</label>
                    <input type="email" name="email" value="<%= e.getEmail() %>">
                </div>

                <div class="form-group">
                    <label>Phone</label>
                    <input type="text" name="phone" value="<%= e.getPhone() %>">
                </div>

                <div class="form-group">
                    <label>Department</label>
                    <input type="text" name="department" value="<%= e.getDepartment() %>">
                </div>

                <div class="form-group">
                    <label>Job Title</label>
                    <input type="text" name="jobTitle" value="<%= e.getJobTitle() %>">
                </div>

                <div class="form-group">
                    <label>Salary</label>
                    <input type="text" name="salary" value="<%= e.getSalary() %>">
                </div>

                <div class="form-group">
                    <label>Date of Joining</label>
                    <input type="date" name="dateOfJoining" value="<%= e.getDateOfJoining() %>">
                </div>

                <div class="form-group full-width">
                    <label>Status</label>
                    <select name="status">
                        <option value="Active" <%= "Active".equals(e.getStatus()) ? "selected" : "" %>>Active</option>
                        <option value="Inactive" <%= "Inactive".equals(e.getStatus()) ? "selected" : "" %>>Inactive</option>
                    </select>
                </div>

            </div>

            <div class="form-actions">
                <input type="submit" value="Update Employee">
            </div>

        </form>
    </div>

</div>

</body>
</html>