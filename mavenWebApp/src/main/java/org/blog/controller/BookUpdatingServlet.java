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
 * Time: 2:43 PM
 * To change this template use File | Settings | File Templates.
 */
@WebServlet(name = "BookUpdatingServlet")
public class BookUpdatingServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        PrintWriter writer = response.getWriter();
        String result = "";
        String htmlResponse = "<html><head><title>Book Application</title><style>form,p {\n" +
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
                "          }</style></head><body>  <div class=\"tab\">\n" +
                "      <button class=\"tablinks\" onclick=\"openHome()\" style=\"margin-left: 300px\">Home</button>\n" +
                "      <button class=\"tablinks\" onclick=\"openUser()\">Users</button>\n" +
                "      <button class=\"tablinks\" onclick=\"openBooks()\">Books</button>"
                + "<button class=\"tablinks\" onclick=\"openReservation()\">Reservation</button>\n" +
                "  </div>";
        String column = request.getParameter("column");
        String value = request.getParameter("valueset");
        value = value.replace(' ','+');
        String modified = request.getParameter("modified");
        modified = modified.replace(' ','+');
        String id = request.getParameter("id");
        String userid = request.getParameter("userId");
        if(value.contains("!") || value.contains("@")||value.equals("#") || value.contains("$")||value.contains("%")||value.equals("^")||value.equals("&"))
        {
            result = "There can be no special characters in " + column;
        }
        else if(value.contains("*")|| value.contains("(")||value.contains(")")||value.contains("?"))
        {
            result = "There can be no special characters in " + column;
        }
        else if(column.equals("title"))
        {
            result = Test.doGet("bookupdate?column=book_name&value="+value+"&id="+id+"&modified="+modified);
        }
        else if(column.equals("author"))
        {
            result = Test.doGet("bookupdate?column=author_name&value="+value+"&id="+id+"&modified="+modified);
        }
        else if(column.equals("year"))
        {
            result = Test.doGet("bookupdate?column=year_published&value="+value+"&id="+id+"&modified="+modified);
        }
        else if(column.equals("genre"))
        {
            result = Test.doGet("bookupdate?column=genre&value="+value+"&id="+id+"&modified="+modified);
        }

        if(result.indexOf("The book has")>=0 || result.contains("special char"))
        {
            htmlResponse+="<p>"+result+"</p>";
        }
        else
        {
            htmlResponse+= "<script>window.location.href = \"http://localhost:8080/mavenWebApp/books.jsp?userId="+userid+"\"</script>";
        }
        htmlResponse+="<script>         function openHome()\n" +
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
                "        }function openReservation(){window.location.href = \"reservation.jsp?userId="+userid+"\";}\n" +
                "     </script></body></html>";
        writer.println(htmlResponse);
    }
}