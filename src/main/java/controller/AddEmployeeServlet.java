package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import util.DBConnection;

public class AddEmployeeServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }

        String role = (String) session.getAttribute("role");
        if (!"admin".equals(role)) {
            response.sendRedirect(request.getContextPath() + "/accessDenied.jsp");
            return;
        }

        response.setContentType("text/html");

        try {
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            String email = request.getParameter("email");
            String phone = request.getParameter("phone");
            String department = request.getParameter("department");
            String jobTitle = request.getParameter("jobTitle");
            String salaryStr = request.getParameter("salary");
            String dojStr = request.getParameter("dateOfJoining");
            String status = request.getParameter("status");

            if (firstName == null || firstName.trim().isEmpty()) {
                response.getWriter().println("First Name is required!");
                return;
            }

            if (email == null || email.trim().isEmpty()) {
                response.getWriter().println("Email is required!");
                return;
            }

            if (phone == null || phone.trim().isEmpty()) {
                response.getWriter().println("Phone is required!");
                return;
            }

            if (salaryStr == null || salaryStr.trim().isEmpty()) {
                response.getWriter().println("Salary is required!");
                return;
            }

            if (dojStr == null || dojStr.trim().isEmpty()) {
                response.getWriter().println("Date of Joining is required!");
                return;
            }

            double salary;
            try {
                salary = Double.parseDouble(salaryStr);
            } catch (NumberFormatException e) {
                response.getWriter().println("Invalid salary! Enter numbers only.");
                return;
            }

            Date doj;
            try {
                doj = Date.valueOf(dojStr);
            } catch (IllegalArgumentException e) {
                response.getWriter().println("Invalid date format!");
                return;
            }

            Connection con = DBConnection.getConnection();

            if (con == null) {
                response.getWriter().println("Database connection failed!");
                return;
            }

            String checkQuery = "SELECT * FROM employee WHERE email = ? OR phone = ?";
            PreparedStatement checkPs = con.prepareStatement(checkQuery);
            checkPs.setString(1, email);
            checkPs.setString(2, phone);

            ResultSet rs = checkPs.executeQuery();

            if (rs.next()) {
                if (email.equals(rs.getString("email"))) {
                    response.getWriter().println("Employee with this email already exists!");
                    return;
                }

                if (phone.equals(rs.getString("phone"))) {
                    response.getWriter().println("Employee with this phone number already exists!");
                    return;
                }
            }

            String insertQuery = "INSERT INTO employee(first_name, last_name, email, phone, department, job_title, salary, date_of_joining, status) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement ps = con.prepareStatement(insertQuery);

            ps.setString(1, firstName);
            ps.setString(2, lastName);
            ps.setString(3, email);
            ps.setString(4, phone);
            ps.setString(5, department);
            ps.setString(6, jobTitle);
            ps.setDouble(7, salary);
            ps.setDate(8, doj);
            ps.setString(9, status);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                session.setAttribute("successMessage", "Employee added successfully!");
                response.sendRedirect(request.getContextPath() + "/viewEmployees");
            } else {
                response.getWriter().println("Failed to add employee!");
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Something went wrong: " + e.getMessage());
        }
    }
}