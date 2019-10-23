package com.github.wenzhencn.cmsseed.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 加密Filter
 */
@Slf4j
public class EncryptionFilter extends GenericFilterBean {

    private EncryptionProperties properties;

    public EncryptionFilter(EncryptionProperties properties) {
        this.properties = properties;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpReq = (HttpServletRequest) request;
        String url = httpReq.getServletPath();
        log.info("EncryptionFilter --- {} ---", url);

        chain.doFilter(new EncryptionServletRequestWrapper(httpReq, this.properties), response);
    }

}
