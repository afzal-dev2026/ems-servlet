package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import util.DBConnection;

public class DashboardServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        System.out.println("Dashboard session user = " + (session == null ? "null" : session.getAttribute("user")));
        System.out.println("Dashboard session role = " + (session == null ? "null" : session.getAttribute("role")));

        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }

        try {
            Connection con = DBConnection.getConnection();

            int totalEmployees = 0;
            int activeEmployees = 0;
            int inactiveEmployees = 0;

            String totalQuery = "SELECT COUNT(*) FROM employee";
            PreparedStatement ps1 = con.prepareStatement(totalQuery);
            ResultSet rs1 = ps1.executeQuery();
            if (rs1.next()) {
                totalEmployees = rs1.getInt(1);
            }

            String activeQuery = "SELECT COUNT(*) FROM employee WHERE status='Active'";
            PreparedStatement ps2 = con.prepareStatement(activeQuery);
            ResultSet rs2 = ps2.executeQuery();
            if (rs2.next()) {
                activeEmployees = rs2.getInt(1);
            }

            String inactiveQuery = "SELECT COUNT(*) FROM employee WHERE status='Inactive'";
            PreparedStatement ps3 = con.prepareStatement(inactiveQuery);
            ResultSet rs3 = ps3.executeQuery();
            if (rs3.next()) {
                inactiveEmployees = rs3.getInt(1);
            }

            request.setAttribute("totalEmployees", totalEmployees);
            request.setAttribute("activeEmployees", activeEmployees);
            request.setAttribute("inactiveEmployees", inactiveEmployees);

            RequestDispatcher rd = request.getRequestDispatcher("home.jsp");
            rd.forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Error: " + e.getMessage());
        }
    }
}