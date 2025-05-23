package com.nisum;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

public class SummaryServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String name = (String) session.getAttribute("name");
        String email = (String) session.getAttribute("email");

        out.println("<h2>Summary</h2>");
        out.println("Name: " + name + "<br>");
        out.println("Email: " + email);
    }
}
