package com.nisum;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class InitParamServlet extends HttpServlet {
    private String adminEmail;
    private String siteName;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        adminEmail = config.getInitParameter("adminEmail");
        siteName = config.getInitParameter("siteName");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h2>Servlet Initialization Parameters</h2>");
        out.println("<p>Admin Email: " + adminEmail + "</p>");
        out.println("<p>Site Name: " + siteName + "</p>");
        out.println("</body></html>");
    }
}
