package com.nisum.controller;

import com.nisum.beans.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ScopeController {

    @Autowired
    SingletonBean singletonBean;

    @Autowired
    PrototypeBean prototypeBean;

    @Autowired
    RequestBean requestBean;

    @Autowired
    SessionBean sessionBean;



    @GetMapping("/scope")
    public String showScopes(Model model) {
        model.addAttribute("singleton", singletonBean.getScope());
        model.addAttribute("prototype", prototypeBean.getScope());
        model.addAttribute("request", requestBean.getScope());
        model.addAttribute("session", sessionBean.getScope());
        //model.addAttribute("globalSession", globalSessionBean.getScope()); not availe
        return "scope";
    }
}
