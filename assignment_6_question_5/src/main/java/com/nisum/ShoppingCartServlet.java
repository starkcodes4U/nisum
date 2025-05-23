package com.nisum;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class ShoppingCartServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String item = request.getParameter("item");
        HttpSession session = request.getSession();

        List<String> cart = (List<String>) session.getAttribute("cart");
        if (cart == null) {
            cart = new ArrayList<>();
        }

        cart.add(item);
        session.setAttribute("cart", cart);

        out.println("<h2>Item added: " + item + "</h2>");
        out.println("<h3>Current Shopping Cart Items:</h3>");
        for (String cartItem : cart) {
            out.println(cartItem + "<br>");
        }
        out.println("<br><a href=\"index.html\">Back to Home</a>");
    }
}
