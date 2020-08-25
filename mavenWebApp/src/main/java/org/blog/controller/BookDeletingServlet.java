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
 * Time: 2:30 PM
 * To change this template use File | Settings | File Templates.
 */
@WebServlet(name = "BookDeletingServlet")
public class BookDeletingServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        String userid = request.getParameter("userId");
        PrintWriter writer = response.getWriter();
        String htmlResponse = "<html><body>";
        String x = Test.doGet("bookdelete?id="+id);

        htmlResponse+= "<script>window.location.href = \"http://localhost:8080/mavenWebApp/books.jsp?userId="+userid+"\"</script>";
        htmlResponse+="</body></html>";
        writer.println(htmlResponse);

    }
}

