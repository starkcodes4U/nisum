package com.nisum;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/feedback2")
public class FeedbackServlet2 extends HttpServlet {

    private static final long serialVersionUID = 1L;

    // Static list to hold all feedback comments (in-memory storage)
    private static final List<String> feedbackList = new ArrayList<>();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Display all feedback
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<html><head><title>Feedback Page</title></head><body>");
        out.println("<h2>Submit your feedback</h2>");

        // Feedback submission form
        out.println("<form method='post' action='feedback2'>");
        out.println("Feedback: <input type='text' name='comment' required />");
        out.println("<input type='submit' value='Submit' />");
        out.println("</form>");

        // Display submitted feedbacks
        out.println("<h3>All Feedbacks</h3>");
        if (feedbackList.isEmpty()) {
            out.println("<p>No feedback submitted yet.</p>");
        } else {
            out.println("<ul>");
            for (String comment : feedbackList) {
                out.println("<li>" + escapeHtml(comment) + "</li>");
            }
            out.println("</ul>");
        }

        out.println("</body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Handle new feedback submission
        String comment = request.getParameter("comment");

        if (comment != null && !comment.trim().isEmpty()) {
            synchronized (feedbackList) {
                feedbackList.add(comment.trim());
            }
        }

        // Redirect to GET to display updated feedback list
        response.sendRedirect("feedback2");
    }

    // Simple HTML escaping to avoid XSS
    private String escapeHtml(String input) {
        if (input == null) return "";
        return input.replaceAll("&", "&amp;")
                .replaceAll("<", "&lt;")
                .replaceAll(">", "&gt;")
                .replaceAll("\"", "&quot;")
                .replaceAll("'", "&#x27;");
    }
}
