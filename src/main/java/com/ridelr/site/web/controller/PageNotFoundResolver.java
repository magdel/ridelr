package com.ridelr.site.web.controller;

import com.ridelr.site.web.web.domain.CommonResponse;
import com.ridelr.site.web.web.domain.ErrorMessage;
import com.ridelr.site.web.web.resolvers.CommonExceptionResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;
import org.springframework.web.servlet.view.xml.MappingJackson2XmlView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class PageNotFoundResolver implements HandlerExceptionResolver {

    private final static Logger LOG = LoggerFactory.getLogger(CommonExceptionResolver.class);

    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        if (!(ex instanceof NoHandlerFoundException)) {
            return null;
        }

        final long errorId = System.currentTimeMillis();
        //LOG.error("Capturing exception " + errorId + ", " + ex.getMessage());

        ModelAndView res;
        String contentType = getContentType(null, request);
        if (contentType != null) {
            if (contentType.contains(MediaType.TEXT_HTML_VALUE)) {
                res = new ModelAndView("notfound");
            } else if (contentType.contains(MediaType.APPLICATION_JSON_VALUE)) {
                MappingJackson2JsonView view = new MappingJackson2JsonView();
                view.setExtractValueFromSingleKeyModel(true);
                res = new ModelAndView(view);
            } else if (contentType.contains(MediaType.APPLICATION_XML_VALUE)) {
                MappingJackson2XmlView view = new MappingJackson2XmlView();
                res = new ModelAndView(view);
            } else {
                res = new ModelAndView("notfound");
            }
        } else {
            res = new ModelAndView("notfound");
        }

        CommonResponse commonCommonResponse = new CommonResponse();
        commonCommonResponse.setError(new ErrorMessage());
        commonCommonResponse.getError().setErrorId(errorId);

        response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        commonCommonResponse.getError().setTechMessage("Not found(");

        res.addObject(commonCommonResponse);
        return res;

    }

    private String getContentType(HandlerMethod method, HttpServletRequest httpServletRequest) {
        if (httpServletRequest.getContentType() != null) {
            return httpServletRequest.getContentType();
        }

        if (httpServletRequest.getHeader(HttpHeaders.ACCEPT) != null) {
            return httpServletRequest.getHeader(HttpHeaders.ACCEPT);
        }

        if (method != null) {
            RequestMapping requestMapping = method.getMethod().getAnnotation(RequestMapping.class);
            if (requestMapping != null && requestMapping.produces().length > 0) {
                return requestMapping.produces()[0];
            }

            requestMapping = method.getBeanType().getAnnotation(RequestMapping.class);
            if (requestMapping != null && requestMapping.produces().length > 0) {
                return requestMapping.produces()[0];
            }
        }

        return null;
    }

}
