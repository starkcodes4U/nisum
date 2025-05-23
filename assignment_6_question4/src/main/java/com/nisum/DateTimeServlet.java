package com.nisum;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@WebServlet("/currentDateTime")
public class DateTimeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");


        LocalDateTime now = LocalDateTime.now();
        String formattedDateTime = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        // Write HTML response
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h2>Current Date and Time:</h2>");
        out.println("<p>" + formattedDateTime + "</p>");
        out.println("</body></html>");
    }
}