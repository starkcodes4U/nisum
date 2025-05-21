package com.nisum;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class UserServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Debug: Print parameters to Tomcat console
        System.out.println("DEBUG - Name: " + request.getParameter("name"));
        System.out.println("DEBUG - Email: " + request.getParameter("email"));

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<html><body>");
        out.println("<h2>Submitted Data:</h2>");
        out.println("<p>Name: " + request.getParameter("name") + "</p>");
        out.println("<p>Email: " + request.getParameter("email") + "</p>");
        out.println("</body></html>");
    }
}