package org.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HelloWorldController {
    @RequestMapping("/Hello")
    public ModelAndView handleHello() {
        String mssg = "Hello World!";
        ModelAndView mav = new ModelAndView("Hello");
        mav.addObject("message", mssg);
        return mav;
    }
}