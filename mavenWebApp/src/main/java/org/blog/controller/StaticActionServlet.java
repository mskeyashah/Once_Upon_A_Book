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
public class StaticActionServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action");
        String id = request.getParameter("user");
        PrintWriter writer = response.getWriter();
        String htmlResponse = "<html><body>";
        if ("Update".equals(action))
        {
            System.out.println(id);
            htmlResponse+="<form method = \"POST\"  action=\"Update\">";
            htmlResponse+="<p>Update User with id = "+id+"</p>\n" +
                    "<input type=\"text\" placeholder=\" First Name\" name=\"first\">"+
                    "<input type=\"text\" placeholder=\"Last Name\" name=\"last\">" +
                    "<input type=\"text\" placeholder=\"UserName\" name=\"username\">" +
                    "<input type=\"text\" placeholder=\"Passphrase\" name=\"passphrase\">" +
                    "<input type=\"text\" placeholder=\"Admin\" name=\"is_admin\">" +
                    "ID: <input type=\"text\" name=\"id\" value=\""+id+"\" readonly>"+
                    "<button type=\"submit\">Update</button>\n" +
                    "<button type=\"button\"  onclick=\"closeForm()\">Cancel</button>\n" +
                    "</form>\n";
            htmlResponse+="<script>function closeForm() {window.location.href = \"http://localhost:8080/mavenWebApp/Start\"}\n";
        }
        else if ("Delete".equals(action))
        {
            htmlResponse+="<script>var r = confirm(\"Are you sure you want to delete the user with id = "+id+"\");\n";
            htmlResponse+="if(r==false){window.location.href = \"http://localhost:8080/mavenWebApp/Start\";}else{window.location.href = \"http://localhost:8080/mavenWebApp/Delete?id="+id+"\";}";

        }
        htmlResponse += "</script></body></html>";
        writer.println(htmlResponse);
    }
}
