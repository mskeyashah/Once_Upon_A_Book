package org.blog.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;


@WebServlet(name = "ReservationSearching")
public class ReservationSearching extends HttpServlet
{
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException,RuntimeException {
        String title = request.getParameter("title");
        String userId = request.getParameter("userId");

        title = title.replace(' ','+');
        String x = "";
        if(title.contains("%")|| title.contains("^"))
            x = "No special characters are allowed in the search field";
        else
            x = Test.doGet("reservationselect?title="+title);
        PrintWriter writer = response.getWriter();
        String htmlResponse = "<html><head><title>Reservation Application</title>";
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
                "      <button class=\"tablinks\" onclick=\"openBooks()\">Books</button>\n" +
                "  <button class=\"tablinks\" onclick=\"openReservation()\">Reservation</button></div>\n" +
                "        </br>\n" +
                "        <form name=\"ReservationSearch\" method=\"GET\" action=\"ReservationSearching\" style=\"font-size: 16;font-family: cursive; font-weight: bold\">\n" +
                "            Title: <input type=\"text\" name=\"title\"/>\n"
                + "<input type=\"hidden\" name=\"userId\" value=\""+userId+"\" >" +
                "            <input type=\"submit\" value=\"Search\" style=\"background-color: limegreen;font-family:cursive; font-size:16;font-weight: bold\"/>\n" +
                "        </form>\n" +
                "        </br></br></br></br></br></br>\n" +
                "     <script>\n" +
                "        function openForm()\n" +
                "        {\n" +
                "            document.getElementById(\"myForm\").style.display = \"block\";\n" +
                "        }\n" +
                "        function closeForm()\n" +
                "        {\n" +
                "            document.getElementById(\"myForm\").style.display = \"none\";\n" +
                "        }\n" +
                "         function openHome()\n" +
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
                "        }</script>";

        if(x.indexOf("No such record")>=0)
        {
            htmlResponse+="<p>Sorry, no such book exists with title = "+title+"</p>";
        }
        else
        {
            String[] res = x.split("}");
            ArrayList<Reservation> reservation = new ArrayList<Reservation>();
            for(int count=0;count<res.length-1;count++)
            {
                String test = res[count];
                String ids = test.substring(test.indexOf("id")+4,test.indexOf("book")-2);
                int id1 = Integer.parseInt(ids);
                String title1 = test.substring(test.indexOf("book")+12,test.indexOf("author")-3);
                String author = test.substring(test.indexOf("author")+9,test.indexOf("year")-3);
                String year = test.substring(test.indexOf("year")+6,test.indexOf("genre")-2);
                String genre = test.substring(test.indexOf("genre")+8,test.indexOf("avail")-3);
                String avail = test.substring(test.indexOf("avail")+11,test.indexOf("num")-2);
                Boolean avail1 = Boolean.parseBoolean(avail);
                String holds = test.substring(test.indexOf("num")+11,test.length());
                int hold1 = Integer.parseInt(holds);
                int year1 = Integer.parseInt(year);
                reservation.add(new Reservation(id1,title1,author,year1,genre,avail1,hold1));
            }
            htmlResponse+="<table style=\"text-align:left;margin-left:275px;\"><tr><th>ID</th><th>Title</th><th>Author</th><th>Year Published</th><th>Genre</th><th>Available</th><th>Num. Holds</th></tr>";
            for(int count = 0;count<reservation.size();count++)
            {
                Reservation temp = reservation.get(count);
                htmlResponse +="<tr><form action=\"ReservationActioning\" method=\"POST\">";
                htmlResponse += "<td><input type = \"radio\" name = \"reserve\" value=\""+temp.getBook_name()+"\">  "+temp.getReservation_id()+ "</td><td>"+temp.getBook_name()+"</td><td>"+temp.getAuthor()+"</td><td>"+temp.getYear()+"</td><td>"+temp.getGenre()+"</td><td>"+temp.getAvailable()+"</td><td>"+temp.getNum_holds()+"</td></tr>";
            }
            htmlResponse += "</table></br></br>"
            		+ "<input type=\"hidden\" name=\"userId\" value=\""+userId+"\" >"
            		+ "<input type=\"submit\" name=\"action\" style=\"background-color: limegreen;font-family:cursive; font-size:16;margin-left:250px;font-weight: bold\" value=\"Reserve Book\">\n" +
                    "    <input type=\"submit\" name=\"action\" style=\"background-color: limegreen;font-family:cursive; font-size:16;font-weight: bold\" value=\"Cancel Reservation\">\n" +
                    "<input type=\"submit\" name=\"action\" style=\"background-color: limegreen;font-family:cursive; font-size:16;font-weight: bold\" value=\"Return Book\">\n" +
                    "</form>";
        }
        htmlResponse+="</body></html>";
        writer.println(htmlResponse);
    }
}