package com.nisum;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class SetSessionServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String username = request.getParameter("username");

        HttpSession session = request.getSession();
        session.setAttribute("username", username);

        out.println("<h3>Session created for: " + username + "</h3>");
        out.println("<a href='readsession'>Go to ReadSessionServlet</a>");
    }
}
