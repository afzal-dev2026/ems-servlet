<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*, model.Employee"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
if (session.getAttribute("user") == null) {
    response.sendRedirect(request.getContextPath() + "/login.jsp");
    return;
}

String role = (String) session.getAttribute("role");
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Employees</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/style.css">
</head>
<body>

<div class="navbar">
    <a href="dashboard">Home</a>

    <% if ("admin".equals(role)) { %>
        <a href="addEmployee.jsp">Add Employee</a>
    <% } %>

    <a href="viewEmployees" class="active">View Employees</a>
    <a href="logout">Logout</a>
</div>

<div class="container">

<%
    String successMessage = (String) session.getAttribute("successMessage");
    if (successMessage != null) {
%>
    <div class="success-message"><%= successMessage %></div>
<%
        session.removeAttribute("successMessage");
    }
%>

    <h2>Employee Directory</h2>
    <p style="text-align:center; color:#64748b; margin-top:-10px; margin-bottom:25px;">
        View and manage employee details in one place.
    </p>

<%
    String keyword = (String) request.getAttribute("keyword");
    if (keyword == null) {
        keyword = "";
    }
%>

    <form action="viewEmployees" method="get" class="search-box">
        <input type="text" name="keyword" placeholder="Search by name or department" value="<%= keyword %>">
        <button type="submit">Search</button>
    </form>

    <div class="table-wrapper">
        <table>
            <tr>
                <th>ID</th>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Email</th>
                <th>Phone</th>
                <th>Department</th>
                <th>Job Title</th>
                <th>Salary</th>
                <th>Date</th>
                <th>Status</th>
                <th>Action</th>
            </tr>

            <c:forEach var="emp" items="${empList}">
                <tr>
                    <td>${emp.id}</td>
                    <td>${emp.firstName}</td>
                    <td>${emp.lastName}</td>
                    <td>${emp.email}</td>
                    <td>${emp.phone}</td>
                    <td>${emp.department}</td>
                    <td>${emp.jobTitle}</td>
                    <td>${emp.salary}</td>
                    <td>${emp.dateOfJoining}</td>
                    <td>${emp.status}</td>
                    <td class="action-links">
                        <% if ("admin".equals(role)) { %>
                            <a href="editEmployee?id=${emp.id}">Edit</a>
                            <a href="deleteEmployee?id=${emp.id}"
                               onclick="return confirm('Are you sure you want to delete this employee?')">
                               Delete
                            </a>
                        <% } else { %>
                            View Only
                        <% } %>
                    </td>
                </tr>
            </c:forEach>

            <c:if test="${empty empList}">
                <tr>
                    <td colspan="11">No Employees Found</td>
                </tr>
            </c:if>

        </table>
    </div>

    <div class="pagination">
<%
    Integer currentPageObj = (Integer) request.getAttribute("currentPage");
    Integer totalPagesObj = (Integer) request.getAttribute("totalPages");

    if (currentPageObj != null && totalPagesObj != null) {
        int currentPage = currentPageObj;
        int totalPages = totalPagesObj;

        for (int i = 1; i <= totalPages; i++) {
            if (i == currentPage) {
%>
                <span class="active-page"><%= i %></span>
<%
            } else {
%>
                <a href="viewEmployees?page=<%= i %>&keyword=<%= keyword %>"><%= i %></a>
<%
            }
        }
    }
%>
    </div>

</div>

</body>
</html>
