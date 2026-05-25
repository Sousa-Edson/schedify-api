package com.schedify.schedify_api.infrastructure.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

@Component
public class RequestResponseLoggingFilter extends OncePerRequestFilter {

    private static final Logger log = LoggerFactory.getLogger(RequestResponseLoggingFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain chain) throws ServletException, IOException {

        var wrappedRequest = new ContentCachingRequestWrapper(request, 10000);
        var wrappedResponse = new ContentCachingResponseWrapper(response);

        Instant start = Instant.now();

        try {
            chain.doFilter(wrappedRequest, wrappedResponse);
        } finally {
            Instant finish = Instant.now();
            long ms = Duration.between(start, finish).toMillis();

            log.info("{} {} -> {} ({}ms)",
                    request.getMethod(), request.getRequestURI(),
                    response.getStatus(), ms);

            byte[] requestBody = wrappedRequest.getContentAsByteArray();
            if (requestBody.length > 0) {
                log.info("  Request body: {}", new String(requestBody, request.getCharacterEncoding()));
            }

            byte[] responseBody = wrappedResponse.getContentAsByteArray();
            if (responseBody.length > 0 && response.getStatus() >= 400) {
                log.warn("  Response body: {}", new String(responseBody, response.getCharacterEncoding()));
            }

            wrappedResponse.copyBodyToResponse();
        }
    }

}
