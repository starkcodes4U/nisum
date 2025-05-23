package com.nisum;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class FirstServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String user = request.getParameter("user");
        request.setAttribute("username", user);

        RequestDispatcher rd = request.getRequestDispatcher("second");
        rd.forward(request, response);
    }
}
