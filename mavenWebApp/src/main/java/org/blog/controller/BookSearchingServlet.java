package org.blog.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;


@WebServlet(name = "BookSearchingServlet")
public class BookSearchingServlet extends HttpServlet
{
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException,RuntimeException {
        String title = request.getParameter("title");
        String id = request.getParameter("userId");
        title = title.replace(' ','+');
        String x = "";
        if(title.contains("%")|| title.contains("^"))
            x = "No special characters are allowed in the search field";
        else
            x = Test.doGet("bookselect?title="+title);
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
                + "<button class=\"tablinks\" onclick=\"openReservation()\">Reservation</button>\n" +
                "  </div>\n" +
                "        </br>\n" +
                "        <form name=\"BookSearch\" method=\"GET\" action=\"BookSearching\" style=\"font-size: 16;font-family: cursive; font-weight: bold\">\n" +
                "            Title: <input type=\"text\" name=\"title\"/>\n" +
                "				<input type=\"hidden\" name=\"userId\" value=\""+id+"\">"+
                "            <input type=\"submit\" value=\"Search\" style=\"background-color: limegreen;font-family:cursive; font-size:16;font-weight: bold\"/>\n" +
                "        </form>\n" +
                "        </br></br></br>\n" +
                "        <p><button class=\"open-button\" onclick=\"openForm()\" style=\"font-family: cursive; font-size: 16;font-weight: bold;\"> Add Book</button></p>\n" +
                "        <div class=\"form-popup\" id=\"myForm\" style=\"background-color: limegreen\">\n" +
                "            <form method = \"GET\"  action=\"BookInserting\" class=\"form-container\">\n" +
                "                <p style=\"font-weight: bold;\">Add Book</p>\n" +
                "                <input type=\"text\" placeholder=\"Book Title\" name=\"title\" required>\n" +
                "                <input type=\"text\" placeholder=\"Author Name\" name=\"author\" required>\n" +
                "                <input type=\"text\" placeholder=\"Year Published\" name=\"year\" required>\n" +
                "                <input type=\"text\" placeholder=\"Genre\" name=\"genre\" required>"+
                "                <input type=\"text\" placeholder=\"Added by\" name=\"added\" required>\n" +
                "				<input type=\"hidden\" name=\"userId\" value=\""+id+"\">"+
                "                <button type=\"submit\" style=\"background-color: beige;font-family: cursive; font-size: 14\">Add Book</button>\n" +
                "                <button type=\"button\" style=\"background-color: beige; font-family: cursive; font-size: 14\" onclick=\"closeForm()\">Close</button>\n" +
                "            </form>\n" +
                "\n" +
                "     </div></br></br></br>\n" +
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
                "             window.location.href = \"index.jsp?userId="+id+"\";\n" +
                "         }\n" +
                "        function openUser()\n" +
                "        {\n" +
                "            window.location.href = \"start.jsp?userId="+id+"\";\n" +
                "        }\n" +
                "        function openBooks()\n" +
                "        {\n" +
                "            window.location.href = \"books.jsp?userId="+id+"\";\n" +
                "        }function openReservation(){window.location.href = \"reservation.jsp?userId="+id+"\";}\n" +
                "     </script>";

        if(x.indexOf("No such record")>=0)
        {
            htmlResponse+="<p>Sorry, no such book exists with title = "+title+"</p>";
        }
        else if(x.contains("special char"))
        {
            htmlResponse+="<p>"+x+"</p>";
        }
        else
        {
            String[] res = x.split("}");
            ArrayList<Books> books = new ArrayList<Books>();
            for(int count=0;count<res.length-1;count++)
            {
                String test = res[count];
                String ids = test.substring(test.indexOf("id")+4,test.indexOf("title")-2);
                int id1 = Integer.parseInt(ids);
                String title1 = test.substring(test.indexOf("title")+8,test.indexOf("author")-3);
                String author = test.substring(test.indexOf("author")+9,test.indexOf("year")-3);
                String year = test.substring(test.indexOf("year")+6,test.indexOf("genre")-2);
                String genre = test.substring(test.indexOf("genre")+8,test.length()-1);
                int year1 = Integer.parseInt(year);
                books.add(new Books(id1,title1,author,year1,genre));
            }
            htmlResponse+="<table style=\"text-align:left;margin-left:275px;\"><tr><th>ID</th><th>Title</th><th>Author</th><th>Year Published</th><th>Genre</th></tr>";
            for(int count = 0;count<books.size();count++)
            {
                Books temp = books.get(count);
                htmlResponse +="<tr><form action=\"BookActioning\" method=\"POST\">";
                htmlResponse += "<td><input type = \"radio\" name = \"book\" value=\""+temp.getId()+"\">  "+temp.getId()+ "</td><td>"+temp.getTitle()+"</td><td>"+temp.getAuthor()+"</td><td>"+temp.getYear()+"</td><td>"+temp.getGenre()+"</td></tr>";
            }
            htmlResponse += "</table></br></br>"
            		+ "<input type=\"hidden\" name=\"userId\" value=\""+id+"\">"
            		+ "<input type=\"submit\" name=\"action\" style=\"background-color: limegreen;font-family:cursive; font-size:16;margin-left:300px;font-weight: bold\" value=\"Update\">\n" +
                    "    <input type=\"submit\" name=\"action\" style=\"background-color: limegreen;font-family:cursive; font-size:16;font-weight: bold\" value=\"Delete\">\n" +
                    "</form>";
        }
        htmlResponse+="</body></html>";
        writer.println(htmlResponse);
    }
}