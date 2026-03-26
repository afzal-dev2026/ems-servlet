package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.*;
import javax.servlet.http.*;

import util.DBConnection;

public class DeleteEmployeeServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
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
            String idStr = request.getParameter("id");

            if (idStr == null) {
                response.getWriter().println("Invalid ID");
                return;
            }

            int id = Integer.parseInt(idStr);

            Connection con = DBConnection.getConnection();

            String query = "DELETE FROM employee WHERE id=?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, id);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                session.setAttribute("successMessage", "Employee deleted successfully!");
                response.sendRedirect(request.getContextPath() + "/viewEmployees");
            } else {
                response.getWriter().println("Delete failed!");
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Error: " + e.getMessage());
        }
    }
}