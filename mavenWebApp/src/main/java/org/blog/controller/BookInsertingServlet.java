package org.blog.controller;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "BookInsertingServlet")
public class BookInsertingServlet extends HttpServlet
{
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException,RuntimeException {
        String title = request.getParameter("title");
        String userid = request.getParameter("userId");
        title = title.replace(' ','+');
        String author = request.getParameter("author");
        author = author.replace(' ','+');
        String year = request.getParameter("year");
        String genre = request.getParameter("genre");
        String added = request.getParameter("added");
        added = added.replace(' ','+');
        String x = Test.doGet("bookinsert?title="+title+"&author="+author+"&year="+year+"&genre="+genre+"&added_by="+added);
        PrintWriter writer = response.getWriter();
        String htmlResponse = "<html><head><title>Book Application</title>";
        htmlResponse+="<style> .form-popup\n" +
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
                "          }\n</style></head><body>";
        htmlResponse+=" <div class=\"tab\">\n" +
                "      <button class=\"tablinks\" onclick=\"openHome()\" style=\"margin-left: 300px\">Home</button>\n" +
                "      <button class=\"tablinks\" onclick=\"openUser()\">Users</button>\n" +
                "      <button class=\"tablinks\" onclick=\"openBooks()\">Books</button>"
                + "<button class=\"tablinks\" onclick=\"openReservation()\">Reservation</button>\n"+
                "  </div>\n" +
                "        </br>\n" +
                "        <form name=\"BookSearch\" method=\"GET\" action=\"BookSearching\" style=\"font-size: 16;font-family: cursive; font-weight: bold\">\n" +
                "            Title: <input type=\"text\" name=\"title\"/>\n"
                + "<input type=\"hidden\" name=\"userId\" value=\""+userid+"\">" +
                "            <input type=\"submit\" value=\"Search\" style=\"background-color: limegreen;font-family:cursive; font-size:16;font-weight: bold\"/>\n" +
                "        </form>\n" +
                "        </br></br></br>\n"+
                "   <script>      function openHome()\n" +
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
                "     </script>";
        if(x.indexOf("The book has")>=0)
        {
            htmlResponse+="<p>The book has already been added.</p>";
        }
        else
        {
            
        	htmlResponse+="<script>window.location.href = \"http://localhost:8080/mavenWebApp/books.jsp?userId="+userid+"\"</script>";
        }
        htmlResponse += "</body></html>";
        writer.println(htmlResponse);
    }
}