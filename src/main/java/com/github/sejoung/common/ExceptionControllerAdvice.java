package com.github.sejoung.common;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ExceptionControllerAdvice {

    public static final String DEFAULT_ERROR_VIEW = "error/error";

    @ExceptionHandler(value = {
            Exception.class
    })
    public ModelAndView handleError(HttpServletRequest request, Exception exception) {
    	exception.printStackTrace();
        ModelAndView mav = new ModelAndView();
        mav.addObject("exception", exception);
        mav.addObject("url", request.getRequestURL());
        mav.setViewName(DEFAULT_ERROR_VIEW);
        return mav;
    }
}
