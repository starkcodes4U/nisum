package com.example.books.security;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.IOException;

public class JwtAuthFilter extends GenericFilter {

    private static final String DEMO_TOKEN = "Bearer demo123";

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        String auth = req.getHeader("Authorization");
        if (DEMO_TOKEN.equals(auth)) {
            chain.doFilter(request, response);
        } else {
            ((HttpServletResponse) response).sendError(HttpServletResponse.SC_UNAUTHORIZED);
        }
    }
}