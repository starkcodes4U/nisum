package com.nisum;



import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;

public class FeedbackServlet extends HttpServlet {
    private static final List<String> feedbackList = new ArrayList<>();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String message = request.getParameter("message");

        String feedback = "Name: " + name + " | Message: " + message;
        feedbackList.add(feedback);

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<h2>Thank you for your feedback!</h2>");
        out.println("<h3>All Feedback Received:</h3>");
        for (String fb : feedbackList) {
            out.println("<p>" + fb + "</p>");
        }
    }
}
