<%@ page import="java.util.List" %>
<%@ page import="com.klef.Book" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Book Search Results</title>
    <style>
        table {
            width: 100%;
            border-collapse: collapse;
        }
        table, th, td {
            border: 1px solid black;
        }
        th, td {
            padding: 10px;
            text-align: left;
        }
        th {
            background-color: #f2f2f2;
        }
    </style>
</head>
<body>
    <h2>Book Search Results</h2>

    <% 
        List<Book> bookList = (List<Book>) request.getAttribute("bookList");
        if (bookList == null || bookList.isEmpty()) {
    %>
        <p>No books found.</p>
    <% 
        } else {
    %>
        <table>
            <thead>
                <tr>
                    <th>Book ID</th>
                    <th>Book Name</th>
                    <th>Book Price</th>
                </tr>
            </thead>
            <tbody>
                <% 
                    for (Book book : bookList) {
                %>
                    <tr>
                        <td><%= book.getBid() %></td>
                        <td><%= book.getBname() %></td>
                        <td><%= book.getBprice() %></td>
                    </tr>
                <% 
                    }
                %>
            </tbody>
        </table>
    <% 
        }
    %>
</body>
</html>
