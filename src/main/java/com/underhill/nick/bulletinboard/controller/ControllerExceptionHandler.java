package com.underhill.nick.bulletinboard.controller;

import com.underhill.nick.bulletinboard.exception.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ModelAndView handleNotFoundException(HttpServletRequest request,
                                                Exception e) {
        ModelAndView modelAndView = new ModelAndView("error/error", HttpStatus.NOT_FOUND);
        modelAndView.addObject("info", e.getMessage());
        return modelAndView;
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ModelAndView handleDataIntegrityViolationException(HttpServletRequest request,
                                                              Exception e) {
        ModelAndView modelAndView = new ModelAndView("error/error", HttpStatus.NOT_FOUND);
        modelAndView.addObject("info",
                "Database error occurred. Please try to change your request or contact site administrator");
        return modelAndView;
    }

}
