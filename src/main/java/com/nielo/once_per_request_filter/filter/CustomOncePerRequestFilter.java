package com.nielo.once_per_request_filter.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.message.Message;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.time.Instant;
import java.util.UUID;

@Component
public class CustomOncePerRequestFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        System.out.println("Request Method: " + request.getMethod());

        System.out.println("Request URI: " + request.getRequestURI());

        String traceId = UUID.randomUUID().toString();

        String timestamp = Instant.now().toString();

        String message = "Hi, I am Niel.";

        response.setHeader("X-Trace-Id", traceId);

        response.setHeader("X-Timestamp", timestamp);

        response.setHeader("Message", message);

        System.out.println("Trace ID: " + traceId);

        System.out.println("Timestamp: " + timestamp);

        System.out.println("Message: " + message);

        filterChain.doFilter(request, response);

        System.out.println("Response Status: " + response.getStatus());

        System.out.println("Response Headers: " + response.getHeaderNames());

        System.out.println("Response Message: " + response.getHeader("Message"));

        System.out.println("Content-Type: " + response.getHeader("Content-Type"));

        System.out.println("Content-Length: " + response.getHeader("Content-Length"));

        System.out.println("Date: " + response.getHeader("Date"));
    }
}
