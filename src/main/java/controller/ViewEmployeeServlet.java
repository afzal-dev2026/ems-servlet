package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.*;
import javax.servlet.http.*;

import model.Employee;
import util.DBConnection;

public class ViewEmployeeServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }

        int page = 1;
        int recordsPerPage = 5;

        if (request.getParameter("page") != null) {
            page = Integer.parseInt(request.getParameter("page"));
        }

        String keyword = request.getParameter("keyword");
        if (keyword == null) {
            keyword = "";
        }

        int start = (page - 1) * recordsPerPage;

        List<Employee> list = new ArrayList<>();

        try {
            Connection con = DBConnection.getConnection();

            String query = "SELECT * FROM employee " +
                           "WHERE first_name LIKE ? OR department LIKE ? " +
                           "LIMIT ?, ?";

            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, "%" + keyword + "%");
            ps.setString(2, "%" + keyword + "%");
            ps.setInt(3, start);
            ps.setInt(4, recordsPerPage);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Employee emp = new Employee();

                emp.setId(rs.getInt("id"));
                emp.setFirstName(rs.getString("first_name"));
                emp.setLastName(rs.getString("last_name"));
                emp.setEmail(rs.getString("email"));
                emp.setPhone(rs.getString("phone"));
                emp.setDepartment(rs.getString("department"));
                emp.setJobTitle(rs.getString("job_title"));
                emp.setSalary(rs.getDouble("salary"));
                emp.setDateOfJoining(rs.getDate("date_of_joining"));
                emp.setStatus(rs.getString("status"));

                list.add(emp);
            }

            String countQuery = "SELECT COUNT(*) FROM employee " +
                                "WHERE first_name LIKE ? OR department LIKE ?";

            PreparedStatement ps2 = con.prepareStatement(countQuery);
            ps2.setString(1, "%" + keyword + "%");
            ps2.setString(2, "%" + keyword + "%");

            ResultSet rs2 = ps2.executeQuery();

            int totalRecords = 0;
            if (rs2.next()) {
                totalRecords = rs2.getInt(1);
            }

            int totalPages = (int) Math.ceil(totalRecords * 1.0 / recordsPerPage);

            request.setAttribute("empList", list);
            request.setAttribute("currentPage", page);
            request.setAttribute("totalPages", totalPages);
            request.setAttribute("keyword", keyword);

            RequestDispatcher rd = request.getRequestDispatcher("viewEmployees.jsp");
            rd.forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Error: " + e.getMessage());
        }
    }
}