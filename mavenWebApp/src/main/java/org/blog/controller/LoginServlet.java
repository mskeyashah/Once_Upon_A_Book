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
@WebServlet(name = "LoginServlet")
public class LoginServlet extends HttpServlet
{
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException,RuntimeException {
        String username = request.getParameter("username");
        String password = request.getParameter("passphrase");
        String x = Test.doGet("login?username="+username+"&passphrase="+password);
        PrintWriter writer = response.getWriter();
        String htmlResponse = "<head><title>Users' Application</title><style>          form,p,a {\n" +
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

        htmlResponse += "<script>function openLogin()\n" +
                "         {\n" +
                "             window.location.href = \"login.jsp\";\n" +
                "         }\n" +
                "        function openUser()\n" +
                "        {\n" +
                "            window.location.href = \"start.jsp\";\n" +
                "        }\n" +
                "        function openBooks()\n" +
                "        {\n" +
                "            window.location.href = \"books.jsp\";\n" +
                "        }function openReservation(){window.location.href = \"reservation.jsp\";}</script>";
        htmlResponse += "<div class=\"tab\">\n" +
                "      <button class=\"tablinks\" onclick=\"openLogin()\" style=\"margin-left: 400px\">Login</button>\n" +
                "  </div>"
                + "</div></br><form name=\"BookSearch\" method=\"POST\" action=\"Login\" style=\"font-size: 16;font-family: cursive; font-weight: bold\">"+
            "Username: <input type=\"text\" name=\"username\" required/>\n"+
            "Password:   <input type=\"text\" name=\"passphrase\" required/>"+
           " <input type=\"submit\" value=\"Login\" style=\"background-color: limegreen;font-family:cursive; font-size:16;font-weight: bold\"/></form></div>";
        if(x.indexOf("Incorrect username or password")>=0)
        {
            htmlResponse+="<p>Incorrect username or password. Please try again.</p>";
        }
        else
        {
      
        	//htmlResponse += "<p>Welcome "+x.substring(x.indexOf('"')+1,x.indexOf(']')-1)+"</p>";
        	String id = x.substring(x.indexOf('[')+1,x.indexOf('"')-1);
        	htmlResponse+="<script> window.location.href=\"reservation.jsp?userId="+id+"\"</script>";
        }
        	
        
        htmlResponse+="</body></html>";
        writer.println(htmlResponse);
    }
}