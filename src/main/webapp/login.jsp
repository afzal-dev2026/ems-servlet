<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/style.css">
</head>
<body>

<div class="simple-page">
    <%
    String error = (String) request.getAttribute("errorMessage");
    if (error != null) {
    %>
        <div class="error-message"><%= error %></div>
    <%
    }
    %>

    <h2>Employee Management System</h2>
    <p style="text-align:center; color:#64748b; margin-bottom:25px;">
        Sign in to continue to your dashboard
    </p>

    <form action="login" method="post">
        Username:
        <input type="text" name="username" placeholder="Enter username">

        Password:
        <input type="password" name="password" placeholder="Enter password">

        <input type="submit" value="Login">
    </form>
</div>

</body>
</html>