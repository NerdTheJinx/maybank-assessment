package org.itsjinxed.assessment.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;

@Slf4j
@Component
public class RequestResponseLoggingFilter extends GenericFilterBean {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        if (request instanceof HttpServletRequest httpRequest
                && response instanceof HttpServletResponse httpResponse) {

            logRequest(httpRequest);

            chain.doFilter(request, response);

            logResponse(httpRequest, httpResponse);
        } else {
            chain.doFilter(request, response);
        }
    }

    private void logRequest(HttpServletRequest request) {
        log.info("Incoming Request: [{}] {}", request.getMethod(), request.getRequestURI());
        request.getHeaderNames().asIterator().forEachRemaining(header ->
                log.info("Header: {} = {}", header, request.getHeader(header))
        );
    }

    private void logResponse(HttpServletRequest request, HttpServletResponse response) {
        var responseWrapper = new ResponseWrapper(response);
        var responseBody = responseWrapper.getBodyAsString();

        log.info("Outgoing Response for [{}]: {} : Status = {}", request.getMethod(), request.getRequestURI(), response.getStatus());

        if (responseBody != null && !responseBody.isEmpty()) {
            log.info("Response Body: {}", responseWrapper.getBodyAsString());
        }
    }
}
