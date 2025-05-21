package com.nisum;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class MethodDemoServlet extends HttpServlet {


    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String name = request.getParameter("name");

        out.println("<html>");
        out.println("<head><title>GET Method Response</title></head>");
        out.println("<body>");
        out.println("<h1>GET Method Demonstration</h1>");
        out.println("<p>You submitted: <strong>" + name + "</strong> via GET method</p>");
        out.println("<p>Notice the parameters are visible in the URL</p>");
        out.println("<a href='index.html'>Back to form</a>");
        out.println("</body>");
        out.println("</html>");
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String email = request.getParameter("email");

        out.println("<html>");
        out.println("<head><title>POST Method Response</title></head>");
        out.println("<body>");
        out.println("<h1>POST Method Demonstration</h1>");
        out.println("<p>You submitted: <strong>" + email + "</strong> via POST method</p>");
        out.println("<p>Notice the parameters are NOT visible in the URL</p>");
        out.println("<a href='index.html'>Back to form</a>");
        out.println("</body>");
        out.println("</html>");
    }
}