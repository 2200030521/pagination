package com.klef;

import javax.servlet.ServletException;
import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/BookServlet")
public class BookServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String keyword = request.getParameter("keyword");
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            // Load database driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish connection
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookdb", "root", "03Nov@2003123");

            // Prepare and execute query
            ps = con.prepareStatement("SELECT * FROM book WHERE bname LIKE ?");
            ps.setString(1, "%" + keyword + "%");
            rs = ps.executeQuery();

            // Initialize list
            List<Book> bookList = new ArrayList<>();

            // Process results
            while (rs.next()) {
                Book book = new Book();
                book.setBid(rs.getInt("bid"));   // Ensure column names match your database schema
                book.setBname(rs.getString("bname"));
                book.setBprice(rs.getDouble("bprice"));
                bookList.add(book);
            }

            // Set request attribute and forward to JSP
            request.setAttribute("bookList", bookList);
            RequestDispatcher rd = request.getRequestDispatcher("bookSearch.jsp");
            rd.forward(request, response);

        } catch (Exception e) {
            e.printStackTrace(); // Handle exception appropriately
        } finally {
            // Close resources
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace(); // Handle exception appropriately
            }
        }
    }
}
