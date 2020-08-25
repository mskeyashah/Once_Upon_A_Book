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
@WebServlet(name = "UpdateServlet")
public class UpdateServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        PrintWriter writer = response.getWriter();
        String result = "";
        String htmlResponse = "<html><head><title>Users' Application</title><style>p{text-align:center;}.tab {\n" +
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
                + "<button class=\"tablinks\" onclick=\"openReservation()\" > Reservation </button>\n" +
                "  </div>";
        String column = request.getParameter("column");
        String value = request.getParameter("valueset");
        String modified_by = request.getParameter("modified_by");
        String id = request.getParameter("id");
        String userId = request.getParameter("userId");
        if(column.equals("first"))
        {
            value = value.replace(' ','+');
            result = Test.doGet("update?column=first_name&value="+value+"&id="+id+"&modified_by="+modified_by);
        }
        else if(column.equals("last"))
        {
            value = value.replace(' ','+');
            result = Test.doGet("update?column=last_name&value="+value+"&id="+id+"&modified_by="+modified_by);
        }
        else if(column.equals("username"))
        {
            if (value.indexOf(' ')>0)
            {
                result = "There are no spaces allowed in the username";
            }
            else
                result = Test.doGet("update?column=username&value="+value+"&id="+id+"&modified_by="+modified_by);
        }
        else if(column.equals("passphrase"))
        {
            if (value.indexOf(' ')>=0)
            {
                result = "There are no spaces allowed in the password";
            }
            else
                result = Test.doGet("update?column=passphrase&value="+value+"&id="+id+"&modified_by="+modified_by);
        }
        else if(column.equals("isadmin"))
        {
            if(value.equals("1") || value.equals("0"))
                result = Test.doGet("update?column=is_admin&value="+value+"&id="+id+"&modified_by="+modified_by);
            else
            {
                result = "Please enter either 1 for admin or 0 for non-admin";
            }
        }

        if(result.indexOf("Error: The value you chose for username is not unique")>=0|| result.contains("There are no spaces") || result.contains("Please enter either"))
        {
            htmlResponse+="<p>"+result+"</p>";
        }
        else
        {
            htmlResponse+= "<script>window.location.href = \"http://localhost:8080/mavenWebApp/start.jsp?userId="+userId+"\"</script>";
        }
        htmlResponse+="<script>         function openHome()\n" +
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
                "        }function openReservation(){window.location.href = \"reservation.jsp?userId="+userId+"\";}\n" +
                "     </script></body></html>";
        writer.println(htmlResponse);
    }
}
