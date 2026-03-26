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
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add Employee</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/style.css">
</head>
<body>

<div class="navbar">
    <a href="dashboard">Home</a>
    <a href="addEmployee.jsp" class="active">Add Employee</a>
    <a href="viewEmployees">View Employees</a>
    <a href="logout">Logout</a>
</div>

<div class="container">

    <h2>Add Employee</h2>
    <p style="text-align:center; color:#64748b; margin-top:-10px; margin-bottom:25px;">
        Fill in the employee details to create a new record in the system.
    </p>

    <div class="form-panel">
        <form action="addEmployee" method="post">

            <div class="form-grid">

                <div class="form-group">
                    <label>First Name</label>
                    <input type="text" name="firstName" placeholder="Enter first name">
                </div>

                <div class="form-group">
                    <label>Last Name</label>
                    <input type="text" name="lastName" placeholder="Enter last name">
                </div>

                <div class="form-group">
                    <label>Email</label>
                    <input type="email" name="email" placeholder="Enter email address">
                </div>

                <div class="form-group">
                    <label>Phone</label>
                    <input type="text" name="phone" placeholder="Enter phone number">
                </div>

                <div class="form-group">
                    <label>Department</label>
                    <input type="text" name="department" placeholder="Enter department">
                </div>

                <div class="form-group">
                    <label>Job Title</label>
                    <input type="text" name="jobTitle" placeholder="Enter job title">
                </div>

                <div class="form-group">
                    <label>Salary</label>
                    <input type="text" name="salary" placeholder="Enter salary">
                </div>

                <div class="form-group">
                    <label>Date of Joining</label>
                    <input type="date" name="dateOfJoining">
                </div>

                <div class="form-group full-width">
                    <label>Status</label>
                    <select name="status">
                        <option value="Active">Active</option>
                        <option value="Inactive">Inactive</option>
                    </select>
                </div>

            </div>

            <div class="form-actions">
                <input type="submit" value="Add Employee">
            </div>

        </form>
    </div>

</div>

</body>
</html>