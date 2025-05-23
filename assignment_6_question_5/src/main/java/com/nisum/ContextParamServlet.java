package com.nisum;


import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


//@WebServlet("/ContextParamServlet")
public class ContextParamServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        ServletContext context = getServletContext();

        String companyName = context.getInitParameter("companyName");
        String supportPhone = context.getInitParameter("supportPhone");

        out.println("<html><body>");
        out.println("<h2>Servlet Context Parameters</h2>");
        out.println("<p>Company Name: " + companyName + "</p>");
        out.println("<p>Support Phone: " + supportPhone + "</p>");
        out.println("</body></html>");
    }
}