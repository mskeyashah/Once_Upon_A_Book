package org.blog.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: keyashah
 * Date: 8/5/19
 * Time: 1:00 PM
 * To change this template use File | Settings | File Templates.
 */
@WebServlet(name = "ReservationActionServlet")
public class ReservationActionServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        String title = request.getParameter("reserve");
        String userId = request.getParameter("userId");
        
        PrintWriter writer = response.getWriter();
        String htmlResponse = "<html><head><title>Reservation Application</title><style>          form,p {\n" +
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
                "      <button class=\"tablinks\" onclick=\"openBooks()\">Books</button>\n" +
                "  <button class=\"tablinks\" onclick=\"openReservation()\">Reservation</button></div>";
        if(title==null || title.isEmpty())
        {
            htmlResponse+="<p>Please choose a book to ";
            if ("Reserve Book".equals(action))
            {
                htmlResponse+= "reserve.</p>";
            }
            else if("Cancel Reservation".equals(action))
            {
                htmlResponse+= "cancel reservation.</p>";
            }
            else
                htmlResponse+= "return.</p>";
        }
        else
        {
            
        	String accesor = Test.doGet("select?id="+userId);
            String first = accesor.substring(accesor.indexOf("first")+8,accesor.indexOf("last")-3);

        	if ("Reserve Book".equals(action))
            {
                htmlResponse+="<p>Reserve book with title = "+title+"</p>" +
                        "<form method=\"post\" action=\"Reserving\" style=\"text-align:center;\">" +
                        "Reservation For "+first+"\n" +
                        "<input type=\"hidden\" name=\"title\" value=\""+title+"\" readonly>"+
                        "<input type=\"hidden\" name=\"userId\" value=\""+userId+"\" >"
                        + "<button type=\"submit\" style=\"background-color: limegreen;font-family:cursive; font-size:16;font-weight: bold\">Reserve</button>\n" +
                        "<button type=\"button\" style=\"background-color: limegreen;font-family:cursive; font-size:16;font-weight: bold\" onclick=\"closeForm()\">Cancel</button>" +
                        "</form>" +
                        "<script>function closeForm() {window.location.href = \"http://localhost:8080/mavenWebApp/reservation.jsp?userId="+userId+"\"}";
            }
            else if ("Cancel Reservation".equals(action))
            {
                htmlResponse+="<p>Cancel Reservation for book with title = "+title+"</p>" +
                        "<form method=\"post\" action=\"Canceling\" style=\"text-align:center;\">" +
                        "Cancel Reservation For "+first+"\n" +
                        "<input type=\"hidden\" name=\"title\" value=\""+title+"\" readonly>"+
                        "<input type=\"hidden\" name=\"userId\" value=\""+userId+"\" >"
                        + "<button type=\"submit\" style=\"background-color: limegreen;font-family:cursive; font-size:16;font-weight: bold\">Confirm</button>\n" +
                        "<button type=\"button\" style=\"background-color: limegreen;font-family:cursive; font-size:16;font-weight: bold\" onclick=\"closeForm()\">Cancel</button>" +
                        "</form>" +
                        "<script>function closeForm() {window.location.href = \"http://localhost:8080/mavenWebApp/reservation.jsp?userId="+userId+"\"}";
            }
            else
            {
                htmlResponse+="<p>Return book with title = "+title+"</p>" +
                        "<form method=\"post\" action=\"Returning\" style=\"text-align:center;\">" +
                        "Return "+first+"'s book\n" +
                        "<input type=\"hidden\" name=\"title\" value=\""+title+"\" readonly>"+
                        "<input type=\"hidden\" name=\"userId\" value=\""+userId+"\" >"
                        + "<button type=\"submit\" style=\"background-color: limegreen;font-family:cursive; font-size:16;font-weight: bold\">Confirm</button>\n" +
                        "<button type=\"button\" style=\"background-color: limegreen;font-family:cursive; font-size:16;font-weight: bold\" onclick=\"closeForm()\">Cancel</button>" +
                        "</form>" +
                        "<script>function closeForm() {window.location.href = \"http://localhost:8080/mavenWebApp/reservation.jsp?userId="+userId+"\"}";
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
                "        }\n" +
                "     function openReservation()\n" +
                "        {\n" +
                "            window.location.href = \"reservation.jsp?userId="+userId+"\"; \n" +
                "        }</script></body></html>";
        writer.println(htmlResponse);
    }
}