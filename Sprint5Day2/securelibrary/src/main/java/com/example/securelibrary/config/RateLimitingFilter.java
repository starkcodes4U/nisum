package com.example.securelibrary.config;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class RateLimitingFilter implements Filter {

    private static final int MAX_REQUESTS_PER_MINUTE = 60;
    private final ConcurrentHashMap<String, AtomicInteger> requestCounts = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<String, Long> timestamps = new ConcurrentHashMap<>();

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        String ip = request.getRemoteAddr();
        long now = System.currentTimeMillis();
        timestamps.putIfAbsent(ip, now);
        requestCounts.putIfAbsent(ip, new AtomicInteger(0));

        long elapsed = now - timestamps.get(ip);
        if (elapsed > 60_000) {
            timestamps.put(ip, now);
            requestCounts.get(ip).set(0);
        }

        int requests = requestCounts.get(ip).incrementAndGet();
        if (requests > MAX_REQUESTS_PER_MINUTE) {
            HttpServletResponse response = (HttpServletResponse) res;
            response.setStatus(429);
            response.getWriter().write("Rate limit exceeded");
            return;
        }

        chain.doFilter(req, res);
    }
}