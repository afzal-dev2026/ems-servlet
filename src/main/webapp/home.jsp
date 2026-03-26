<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
if (session.getAttribute("user") == null) {
    response.sendRedirect(request.getContextPath() + "/login.jsp");
    return;
}

String role = (String) session.getAttribute("role");
String username = (String) session.getAttribute("user");
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Home</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/style.css">
</head>
<body>

<div class="navbar">
    <a href="dashboard" class="active">Home</a>

    <% if ("admin".equals(role)) { %>
        <a href="addEmployee.jsp">Add Employee</a>
    <% } %>

    <a href="viewEmployees">View Employees</a>
    <a href="logout">Logout</a>
</div>

<div class="container">

    <h2 class="dashboard-title">Welcome, <%= username %></h2>
    <p style="text-align:center; color:#64748b; margin-top:-15px; margin-bottom:30px;">
        Manage employee records, monitor workforce status, and handle daily operations efficiently.
    </p>

<%
    Integer totalEmployees = (Integer) request.getAttribute("totalEmployees");
    Integer activeEmployees = (Integer) request.getAttribute("activeEmployees");
    Integer inactiveEmployees = (Integer) request.getAttribute("inactiveEmployees");

    if (totalEmployees == null) totalEmployees = 0;
    if (activeEmployees == null) activeEmployees = 0;
    if (inactiveEmployees == null) inactiveEmployees = 0;
%>

    <div class="summary-cards">
        <div class="summary-card">
            <h3>Total Employees</h3>
            <p><%= totalEmployees %></p>
        </div>

        <div class="summary-card">
            <h3>Active Employees</h3>
            <p><%= activeEmployees %></p>
        </div>

        <div class="summary-card">
            <h3>Inactive Employees</h3>
            <p><%= inactiveEmployees %></p>
        </div>
    </div>

    <div class="dashboard-cards">

        <% if ("admin".equals(role)) { %>
            <div class="dashboard-card">
                <h3>Add Employee</h3>
                <p>Create a new employee record and add them to the organization database.</p>
                <a href="addEmployee.jsp">Open</a>
            </div>
        <% } %>

        <div class="dashboard-card">
            <h3>View Employees</h3>
            <p>Browse employee details, search records, and manage existing staff entries.</p>
            <a href="viewEmployees">Open</a>
        </div>

        <div class="dashboard-card">
            <h3>Logout</h3>
            <p>End the current session securely and return to the login screen.</p>
            <a href="logout">Logout</a>
        </div>

    </div>

</div>

</body>
</html>