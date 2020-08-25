package org.blog.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;


@WebServlet(name = "SearchingServlet")
public class SearchingServlet extends HttpServlet
{
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException,RuntimeException {
        String id = request.getParameter("id");
        String userId = request.getParameter("userId");
        String x = Test.doGet("select?id="+id);
        PrintWriter writer = response.getWriter();
        String htmlResponse = "<html><head><title>Users' Application</title>";
        htmlResponse+="<style>.form-popup\n" +
                "          {\n" +
                "            display: none;\n" +
                "            border: 3px solid black;\n" +
                "            z-index: 9;\n" +
                "            text-align: center;\n" +
                "          }\n" +
                "         form,p {\n" +
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
                "          }</style></head>";
        htmlResponse+="<div class=\"tab\">\n" +
                "      <button class=\"tablinks\" onclick=\"openHome()\" style=\"margin-left: 300px\">Home</button>\n" +
                "      <button class=\"tablinks\" onclick=\"openUser()\">Users</button>\n" +
                "      <button class=\"tablinks\" onclick=\"openBooks()\">Books</button>"
                + "<button class=\"tablinks\" onclick=\"openReservation()\"> Reservation </button>\n" +
                "  </div></br>\n" +
                "     <form name=\"Search\" method=\"GET\" action=\"Searching\" style=\"font-size: 16;font-family: cursive; text-align:center;font-weight: bold\">\n" +
                "      ID: <input type=\"number\" name=\"id\"/>\n" +
                "		<input type=\"hidden\" name=\"userId\" value=\""+userId+"\">"
                +
                "      <input type=\"submit\" value=\"Search\" style=\"background-color: limegreen;font-family:cursive;font-size:16;font-weight: bold\"/> </form>\n" +
                "     </br></br></br><p><button class=\"open-button\" onclick=\"openForm()\" style=\"font-family: cursive; font-size: 16;font-weight: bold;\"> Add User</button></p>\n" +
                "     <div class=\"form-popup\" id=\"myForm\" style=\"background-color: limegreen\">\n" +
                "         <form method = \"GET\"  action=\"Inserting\" class=\"form-container\">\n" +
                "             <p style=\"font-weight: bold\">Add User</p>\n" +
                "             <input type=\"text\" placeholder=\" First Name\" name=\"first\" required>\n" +
                "             <input type=\"text\" placeholder=\"Last Name\" name=\"last\" required>\n" +
                "             <input type=\"text\" placeholder=\"UserName\" name=\"username\" required>\n" +
                "             <input type=\"text\" placeholder=\"Passphrase\" name=\"passphrase\" required>\n" +
                "             <input type=\"text\" placeholder=\"Admin\" name=\"is_admin\" required>\n"
                + "<input type=\"hidden\" name=\"userId\" value=\""+userId+"\">" +
                "             <button type=\"submit\" style=\"background-color: beige;font-family: cursive; font-size: 14\">Add</button>\n" +
                "             <button type=\"button\" style=\"background-color: beige; font-family: cursive; font-size: 14\" onclick=\"closeForm()\">Close</button>\n" +
                "         </form></div>\n" +
                "     <script>\n" +
                "     function openForm() {\n" +
                "         document.getElementById(\"myForm\").style.display = \"block\";}\n" +
                "     function closeForm() {\n" +
                "         document.getElementById(\"myForm\").style.display = \"none\";\n" +
                "      }" +
                "function openHome()\n" +
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
                "     </script></br></br></br>";

      
        	
	    	if(x.indexOf("No such record")>=0)
	        {
	            htmlResponse+="<p>Sorry, no such record exists with id = "+id+"</p>";
	        }
	        else
	        {
	           String accesor = Test.doGet("select?id="+userId);
	           
	        	String[] res = x.split("}");
	            ArrayList<User> users = new ArrayList<User>();
	      
	            for(int count=0;count<res.length-1;count++)
	            {
	               String test = res[count];
	               
	                String ids = test.substring(test.indexOf("id")+4,test.indexOf("first")-2);
	                int id1 = Integer.parseInt(ids);
	                String first = test.substring(test.indexOf("first")+8,test.indexOf("last")-3);
	                String last = test.substring(test.indexOf("last")+7,test.indexOf("username")-3);
	                String username = test.substring(test.indexOf("username")+11,test.indexOf("passphrase")-3);
	                String passphrase = test.substring(test.indexOf("passphrase")+13,test.indexOf("isAdmin")-3);
	                String isAdmin = test.substring(test.indexOf("isAdmin")+9);
	                Boolean admin = Boolean.parseBoolean(isAdmin);
	                users.add(new User(id1,first,last,username,passphrase,admin));
	            }
	        
	    	
	            if(userId.equals(id) == true || accesor.contains("true"))
	            {
	            	htmlResponse+="<table style=\"text-align:left;margin-left:275px;\"><tr><th>ID</th><th>First Name</th><th>Last Name</th><th>Username</th><th>Passphrase</th><th>Admin</th></tr>";
	            	for(int count = 0;count<users.size();count++)
	            	{
	            		User temp = users.get(count);
	            		htmlResponse +="<tr><form action=\"Actioning\" method=\"POST\">";
	            		htmlResponse += "<td><input type = \"radio\" name = \"user\" value=\""+temp.getId()+"\">  "+temp.getId()+ "</td><td>"+temp.getFirst()+"</td><td>"+temp.getLast()+"</td><td>"+temp.getUsername()+"</td><td>"+temp.getPassphrase()+"</td><td>"+temp.getIsAdmin()+"</td></tr>";
	            	}
	            	htmlResponse += "</table></br></br>"
	            			+ "<input type=\"hidden\" name=\"userId\" value=\""+userId+"\">"
	            					+ "<input type=\"submit\" name=\"action\" style=\"background-color: limegreen;font-family:cursive; font-size:16;margin-left:350px;font-weight: bold\" value=\"Update\">\n" +
	                    "    <input type=\"submit\" name=\"action\" style=\"background-color: limegreen;font-family:cursive; font-size:16;font-weight: bold\" value=\"Delete\">\n" +
	                    "</form>";
	            }
	        
	            else
	            {
	            	if(id.equals(""))
	            		htmlResponse+="<p>Sorry, you are not allowed to access all records.</p>";
	            	else
	            		htmlResponse+="<p>Sorry, you are not allowed to access person with id = "+id+ " record.</p>";
	            }
	    }
        htmlResponse+="</body></html>";
        writer.println(htmlResponse);
    }
}