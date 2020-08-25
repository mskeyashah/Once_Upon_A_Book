package org.blog.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created with IntelliJ IDEA.
 * User: keyashah
 * Date: 8/5/19
 * Time: 1:00 PM
 * To change this template use File | Settings | File Templates.
 */
@WebServlet(name = "BookActionServlet")
public class BookActionServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        String id = request.getParameter("book");
        String userid = request.getParameter("userId");
        PrintWriter writer = response.getWriter();
        String htmlResponse = "<html><head><title>Book Application</title><style>.form-popup\n" +
                "          {\n" +
                "            display: none;\n" +
                "            border: 3px solid black;\n" +
                "            z-index: 9;\n" +
                "            text-align: center;\n" +
                "          }\n" +
                "          form,p {\n" +
                "              text-align: center;\n" +
                "          }\n" +
                "          .open-button  {\n" +
                "              text-align: center;\n" +
                "              background-color: limegreen;\n" +
                "              text-decoration-color: black;\n" +
                "          }\n" +
                "          .tab {\n" +
                "              overflow: hidden;\n" +
                "              border: 3px solid black;\n" +
                "              background-color: limegreen;\n" +
                "              text-align: center;\n" +
                "          }\n" +
                "          .tab button {\n" +
                "              background-color: inherit;\n" +
                "              float: left;\n" +
                "              border: none;\n" +
                "              outline: none;\n" +
                "              cursor: pointer;\n" +
                "              padding: 14px 16px;\n" +
                "              transition: 0.3s;\n" +
                "              font-size: 17px;\n" +
                "          }\n" +
                "          .tab button:hover {\n" +
                "              background-color: greenyellow;\n" +
                "          }\n" +
                "</style></head><body>  <div class=\"tab\">\n" +
                "      <button class=\"tablinks\" onclick=\"openHome()\" style=\"margin-left: 300px\">Home</button>\n" +
                "      <button class=\"tablinks\" onclick=\"openUser()\">Users</button>\n" +
                "      <button class=\"tablinks\" onclick=\"openBooks()\">Books</button>\n"
                + "<button class=\"tablinks\" onclick=\"openReservation()\">Reservation</button>" +
                "  </div>";
        if(id==null || id.isEmpty())
        {
            htmlResponse+="<p>Please choose a book to ";
            if ("Update".equals(action))
            {
                htmlResponse+= "update.</p>";
            }
            else
                htmlResponse+= "delete.</p>";
        }
        else
        {
            if ("Update".equals(action))
            {
                htmlResponse+="<p>Update book with id = "+id+"</p>" +
                        "<form method=\"post\" action=\"BookUpdating\" style=\"text-align:center;\">" +
                        "<select name=\"column\">" +
                        "<option value=\"title\">Title</option>" +
                        "<option value=\"author\">Author</option>" +
                        "<option value=\"year\">Year</option>" +
                        "<option value=\"genre\">Genre</option>" +
                        "<input type=\"text\" placeholder=\"Value\" name=\"valueset\" required>\n" +
                        "<input type=\"text\" placeholder=\"Modified By\" name=\"modified\" required>\n"
                        + "<input type=\"hidden\" name=\"userId\" value=\""+userid+"\">" +
                        "<input type=\"hidden\" name=\"id\" value=\""+id+"\" readonly>"+
                        "<button type=\"submit\" style=\"background-color: limegreen;font-family:cursive; font-size:16;font-weight: bold\">Update</button>\n" +
                        "<button type=\"button\" style=\"background-color: limegreen;font-family:cursive; font-size:16;font-weight: bold\" onclick=\"closeForm()\">Cancel</button>" +
                        "</form>" +
                        "<script>function closeForm() {window.location.href = \"http://localhost:8080/mavenWebApp/books.jsp?userId="+userid+"\"}";
            }
            else if ("Delete".equals(action))
            {
                htmlResponse+="<script>var r = confirm(\"Are you sure you want to delete the book with id = "+userid+"\");\n";
                htmlResponse+="if(r==false){window.location.href = \"http://localhost:8080/mavenWebApp/books.jsp?userId="+userid+"\";}else{window.location.href = \"http://localhost:8080/mavenWebApp/BookDeleting?id="+id+"&userId="+userid+"\";}";

            }
            htmlResponse += "</script>";
        }
        htmlResponse+= "<script>function openHome()\n" +
                "         {\n" +
                "             window.location.href = \"index.jsp?userId="+userid+"\";\n" +
                "         }\n" +
                "        function openUser()\n" +
                "        {\n" +
                "            window.location.href = \"start.jsp?userId="+userid+"\";\n" +
                "        }\n" +
                "        function openBooks()\n" +
                "        {\n" +
                "            window.location.href = \"books.jsp?userId="+userid+"\";\n" +
                "        }function openReservation(){window.location.href = \"reservation.jsp?userId="+userid+"\";}</script></body></html>";
        writer.println(htmlResponse);
    }
}