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
 * Date: 8/8/19
 * Time: 11:12 AM
 * To change this template use File | Settings | File Templates.
 */
@WebServlet(name = "InsertServlet")
public class InsertServlet extends HttpServlet
{
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException,RuntimeException {
        String first = request.getParameter("first");
        String last = request.getParameter("last");
        String username = request.getParameter("username");
        String password = request.getParameter("passphrase");
        String admin = request.getParameter("is_admin");
        String userId = request.getParameter("userId");
  
        String x = Test.doGet("insert?first="+first+"&last="+last+"&username="+username+"&passphrase="+password+"&isadmin="+admin);
        PrintWriter writer = response.getWriter();
        String htmlResponse = "<head><title>Users' Application</title><style>          form,p {\n" +
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
                "          }\n</style></head><body>  <div class=\"tab\">\n" +
                "      <button class=\"tablinks\" onclick=\"openHome()\" style=\"margin-left: 300px\">Home</button>\n" +
                "      <button class=\"tablinks\" onclick=\"openUser()\">Users</button>\n" +
                "      <button class=\"tablinks\" onclick=\"openBooks()\">Books</button>"
                + "<button class=\"tablinks\" onclick=\"openReservation()\" > Reservation </button>\n" +
                "  </div>";
        if(x.indexOf("The username is not")>=0)
        {
            htmlResponse+="<p>The username you chose is not available. Please try again.</p>";
        }
        else
            htmlResponse+="<script>window.location.href = \"http://localhost:8080/mavenWebApp/start.jsp?userId="+userId+"\"</script>";
        htmlResponse += "function openHome()\n" +
                "         {\n" +
                "             window.location.href = \"home.jsp?userId="+userId+"\";\n" +
                "         }\n" +
                "        function openUser()\n" +
                "        {\n" +
                "            window.location.href = \"start.jsp?userId="+userId+"\";\n" +
                "        }\n" +
                "        function openBooks()\n" +
                "        {\n" +
                "            window.location.href = \"books.jsp?userId="+userId+"\";\n" +
                "        }function openReservation(){window.location.href = \"reservation.jsp?userId="+userId+"\";}</body></html>";
        writer.println(htmlResponse);
    }
}