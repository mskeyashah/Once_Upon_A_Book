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
 * Time: 2:43 PM
 * To change this template use File | Settings | File Templates.
 */
@WebServlet(name = "UpdateServlet")
public class StaticUpdateServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        PrintWriter writer = response.getWriter();
        ArrayList<Object> result = new ArrayList<Object>();
        String htmlResponse = "<html><body>";
        String first = request.getParameter("first");
        String last = request.getParameter("last");
        String username = request.getParameter("username");
        String passphrase = request.getParameter("passphrase");
        String is_admin = request.getParameter("is_admin");
        String id = request.getParameter("id");
        User x =  (User) StaticMethods.select(id).get(0);
        if(!first.equals(x.getFirst()))
        {
            result = StaticMethods.update("first_name",first,id);
        }
        else if(!last.equals(x.getLast()))
        {
            result = StaticMethods.update("last_name",last,id);
        }
        else if(!username.equals(x.getUsername()))
        {
            result = StaticMethods.update("username",username,id);
        }
        else if(!passphrase.equals(x.getPassphrase()))
        {
            result = StaticMethods.update("passphrase",passphrase,id);
        }
        else if(!is_admin.equals(x.getIsAdmin()))
        {
            result = StaticMethods.update("is_admin",is_admin,id);
        }

        if(result.get(0).equals("Error: The value you chose for username is not unique"))
        {
            htmlResponse+="<p>"+result.get(0)+"</p>";
        }
        else
        {
            htmlResponse+= "<script>window.location.href = \"http://localhost:8080/mavenWebApp/Search?id=&query=update\"</script>";
        }
        htmlResponse+="</body></html>";
        writer.println(htmlResponse);
    }
}