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
 * Time: 9:59 AM
 * To change this template use File | Settings | File Templates.
 */
@WebServlet(name = "InsertServlet")
public class StaticInsertServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String first = request.getParameter("first");
        String last = request.getParameter("last");
        String username = request.getParameter("username");
        String password = request.getParameter("passphrase");
        String admin = request.getParameter("is_admin");

        ArrayList<Object> result = StaticMethods.insert(first,last,username,password,admin);
        PrintWriter writer = response.getWriter();
        String htmlResponse = "<html><body>";
        if(result.get(0).equals("The username is not available"))
        {
            htmlResponse+="<p>"+result.get(0)+"</p>";
        }
        else
            htmlResponse+="<script>window.location.href = \"http://localhost:8080/mavenWebApp/Search?id=&query=insert\"</script>";
        htmlResponse += "</body></html>";
        writer.println(htmlResponse);

    }
}