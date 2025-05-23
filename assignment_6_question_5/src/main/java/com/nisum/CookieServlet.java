package com.nisum;


import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/cookie")
public class CookieServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        Cookie userCookie = new Cookie("username", "LetMeIn!!");
        userCookie.setMaxAge(60 * 60);
        response.addCookie(userCookie);
        out.println("<html><body>");
        out.println("<h2>This is a Cookie</h2>");
        out.println("<p>Cookie 'username' set.</p>");

        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            out.println("<h3>Received Cookies:</h3>");
            for (Cookie cookie : cookies) {
                out.println("<p>" + cookie.getName() + ": " + cookie.getValue() + "</p>");
            }
        } else {
            out.println("<p>No cookies received.</p>");
        }

        out.println("</body></html>");
    }
}