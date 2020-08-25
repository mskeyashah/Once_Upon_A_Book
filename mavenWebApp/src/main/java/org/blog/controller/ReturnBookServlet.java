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
@WebServlet(name = "ReturnBookServlet")
public class ReturnBookServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        PrintWriter writer = response.getWriter();
        String result = "";
        String userId = request.getParameter("userId");
        String htmlResponse = "<html><head><title>Reservation Application</title><style>form,p {\n" +
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
                "      <button class=\"tablinks\" onclick=\"openBooks()\">Books</button>\n" +
                "  <button class=\"tablinks\" onclick=\"openReservation()\">Reservation</button></div>";

       
        String title = request.getParameter("title");
        title = title.replace(' ','+');
        String accesor = Test.doGet("select?id="+userId);
        String first = accesor.substring(accesor.indexOf("first")+8,accesor.indexOf("last")-3);
        result = Test.doGet("returningbook?title="+title+"&name="+first);
        

        if(result.contains("The holder of this book"))
        {
            htmlResponse+= "<p>"+result.substring(2,result.length()-2)+"</p>";
        }
        else
            htmlResponse+= "<p>The book has been returned.</p>";

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
                "        }\n" +
                "     function openReservation()\n" +
                "        {\n" +
                "            window.location.href = \"reservation.jsp?userId="+userId+"\";\n" +
                "        }</script></body></html>";
        writer.println(htmlResponse);
    }
}