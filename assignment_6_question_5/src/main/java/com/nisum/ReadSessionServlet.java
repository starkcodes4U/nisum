package com.nisum;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class ReadSessionServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        HttpSession session = request.getSession(false);

        if (session != null) {
            String username = (String) session.getAttribute("username");
            out.println("<h3>Welcome123, " + username + "</h3>");
        } else {
            out.println("<h3>No session found</h3>");
        }
    }
}
