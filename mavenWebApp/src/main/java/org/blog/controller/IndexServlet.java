package org.blog.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet(name = "IndexServlet")
public class IndexServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter writer = response.getWriter();
        String htmlResponse = "<html>" +
                "<head>" +
                "<style>" +
                ".form-popup {\n" +
                "  display: none;\n" +
                "  position: fixed;\n" +
                "  top: 120px;\n" +
                "  left: 15px;\n" +
                "  border: 3px solid #f1f1f1;\n" +
                "  z-index: 9;\n" +
                "}";
        htmlResponse += "</style>" +
                "</head>" +
                "<body>" +
                "<form name=\"Search\" method=\"GET\" action=\"Search\">\n" +
                "        ID: <input type=\"text\" name=\"id\"/>" +
                "        <input type=\"submit\" value=\"Search\" /> </form>" +
                "</br></br></br><button class=\"open-button\" onclick=\"openForm()\"> Add User</button>\n" +
                "\n" +
                "<div class=\"form-popup\" id=\"myForm\">\n" +
                "  <form method = \"POST\"  action=\"Insert\" class=\"form-container\">\n" +
                "    <p>Add User</p>\n" +
                "\n" +
                "    <input type=\"text\" placeholder=\" First Name\" name=\"first\">\n" +
                "\n" +
                "    <input type=\"text\" placeholder=\"Last Name\" name=\"last\">\n" +
                "    <input type=\"text\" placeholder=\"UserName\" name=\"username\">\n" +
                "    <input type=\"text\" placeholder=\"Passphrase\" name=\"passphrase\">\n" +
                "    <input type=\"text\" placeholder=\"Admin\" name=\"is_admin\">\n" +
                "\n" +
                "    <button type=\"submit\" class=\"btn\">Add</button>\n" +
                "    <button type=\"button\" class=\"btn cancel\" onclick=\"closeForm()\">Close</button>\n" +
                "  </form>\n" +
                "</div>\n" +
                "\n" +
                "<script>\n" +
                "function openForm() {\n" +
                "  document.getElementById(\"myForm\").style.display = \"block\";\n" +
                "}\n" +
                "\n" +
                "function closeForm() {\n" +
                "  document.getElementById(\"myForm\").style.display = \"none\";\n" +
                "}\n" +
                "</script>";
        htmlResponse += "</body>" +
                "</html>";

        // return response
        writer.println(htmlResponse);

    }
}