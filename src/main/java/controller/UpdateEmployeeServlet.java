package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;

import javax.servlet.*;
import javax.servlet.http.*;

import util.DBConnection;

public class UpdateEmployeeServlet extends HttpServlet {

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

        try {
            int id = Integer.parseInt(request.getParameter("id"));

            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            String email = request.getParameter("email");
            String phone = request.getParameter("phone");
            String department = request.getParameter("department");
            String jobTitle = request.getParameter("jobTitle");
            double salary = Double.parseDouble(request.getParameter("salary"));
            Date doj = Date.valueOf(request.getParameter("dateOfJoining"));
            String status = request.getParameter("status");

            Connection con = DBConnection.getConnection();

            String query = "UPDATE employee SET first_name=?, last_name=?, email=?, phone=?, department=?, job_title=?, salary=?, date_of_joining=?, status=? WHERE id=?";

            PreparedStatement ps = con.prepareStatement(query);

            ps.setString(1, firstName);
            ps.setString(2, lastName);
            ps.setString(3, email);
            ps.setString(4, phone);
            ps.setString(5, department);
            ps.setString(6, jobTitle);
            ps.setDouble(7, salary);
            ps.setDate(8, doj);
            ps.setString(9, status);
            ps.setInt(10, id);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                session.setAttribute("successMessage", "Employee updated successfully!");
                response.sendRedirect(request.getContextPath() + "/viewEmployees");
            } else {
                response.getWriter().println("Update failed!");
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Error: " + e.getMessage());
        }
    }
}