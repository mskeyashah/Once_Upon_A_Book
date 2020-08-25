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
@WebServlet(name = "ActionServlet")
public class ActionServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        String id = request.getParameter("user");
        String userId = request.getParameter("userId");
        PrintWriter writer = response.getWriter();
        String htmlResponse = "<html><head><title>Users' Application</title><style>          form,p {\n" +
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
                "          }" +
                "</style></head><body> <div class=\"tab\">\n" +
                "      <button class=\"tablinks\" onclick=\"openHome()\" style=\"margin-left: 300px\">Home</button>\n" +
                "      <button class=\"tablinks\" onclick=\"openUser()\">Users</button>\n" +
                "      <button class=\"tablinks\" onclick=\"openBooks()\">Books</button>\n"
                + "<button class=\"tablinks\" onclick=\"openReservation()\" > Reservation </button>" +
                "  </div>";
        if(id==null || id.isEmpty())
        {
            htmlResponse+="<p>Please choose a user to ";
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
               htmlResponse+="<p>Update user with id = "+id+"</p>" +
                       "<form method=\"post\" action=\"Updating\" style=\"text-align:center;\">" +
                       "<select name=\"column\">" +
                       "<option value=\"first\">First Name</option>" +
                       "<option value=\"last\">Last Name</option>" +
                       "<option value=\"username\">Username</option>" +
                       "<option value=\"passphrase\">Passphrase</option>" +
                       "<option value=\"isadmin\">Admin</option></select>" +
                       "<input type=\"text\" placeholder=\"Value\" name=\"valueset\" required>\n" +
                       "<input type=\"text\" placeholder=\"Modified By\" name=\"modified\" required>\n" +
                       "<input type=\"hidden\" name=\"id\" value=\""+id+"\" readonly>"+
                       "<input type=\"hidden\" name=\"userId\" value=\""+userId+"\">"
                       		+ "<button type=\"submit\" style=\"background-color: limegreen;font-family:cursive; font-size:16;font-weight: bold\">Update</button>\n" +
                       "<button type=\"button\" style=\"background-color: limegreen;font-family:cursive; font-size:16;font-weight: bold\" onclick=\"closeForm()\">Cancel</button>" +
                       "</form>" +
                       "<script>function closeForm() {window.location.href = \"http://localhost:8080/mavenWebApp/start.jsp?userId="+userId+"\"}";
            }
            else if ("Delete".equals(action))
            {
               if(userId.equals(id))
               {
            	   htmlResponse+="<p>You cannot delete your own record</p>";
               }
               else
               {
            	   htmlResponse+="<script>var r = confirm(\"Are you sure you want to delete the user with id = "+id+"\");\n";
            	   htmlResponse+="if(r==false){window.location.href = \"http://localhost:8080/mavenWebApp/start.jsp?userId="+userId+"\";}"
            	   		+ "else{window.location.href = \"http://localhost:8080/mavenWebApp/Deleting?id="+id+"&userId="+userId+"\";}";
               }
            }
            htmlResponse += "</script>";
        }
        htmlResponse+= "<script>         function openHome()\n" +
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
                "        }"
                + "function openReservation(){window.location.href = \"reservation.jsp?userId="+userId+"\";}\n" +
                "     </script></body></html>";
        writer.println(htmlResponse);
    }
}
