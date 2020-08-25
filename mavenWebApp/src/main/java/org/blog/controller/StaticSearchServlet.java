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
 * Time: 9:10 AM
 * To change this template use File | Settings | File Templates.
 */
@WebServlet(name = "SearchServlet")
public class StaticSearchServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        String query = request.getParameter("query");
        ArrayList<Object> result= new ArrayList<Object>();
        if(id == null || id.isEmpty())
        {
            result = StaticMethods.select("all");
        }
        else
            result = StaticMethods.select(id);

        if(query==null || query.isEmpty())
            query = "select";
        PrintWriter writer = response.getWriter();
        String htmlResponse = "<html><body>";
        if(result.get(0).equals("No such record exists with id = "+id))
        {
            htmlResponse += result.get(0);
            for(int x =0;x<25;x++)
            {
                htmlResponse+="</br>";
            }
            htmlResponse+="<div style=\"bottom:0px; left:0px; width:400px; height:20px;background-color:gray \">Status: code=200 query=\""+query+"\"";
            htmlResponse+=" count=0";
        }
        else
        {

            for(int count = 0;count<result.size();count++)
            {
                Object temp = result.get(count);
                htmlResponse +="<form action=\"Action\" method=\"POST\">";
                htmlResponse += "<input type = \"radio\" name = \"user\" value=\""+((User) temp).getId()+"\">"+temp+ "</br>";
            }
            htmlResponse += "</br></br><input type=\"submit\" name=\"action\" value=\"Update\">\n" +
                    "    <input type=\"submit\" name=\"action\" value=\"Delete\"> \n" +
                    "</form>";
            htmlResponse+="<div style=\"bottom:0px; left:0px; width:400px; height:20px;background-color:gray \">Status: code=200 query=\""+query+"\"";
            htmlResponse+=" count="+result.size();
        }
        htmlResponse+="</div>";
        htmlResponse += "</body></html>";
        writer.println(htmlResponse);

    }

}
