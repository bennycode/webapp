package com.welovecoding.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DefaultController {

  @RequestMapping(method = RequestMethod.GET, value = "/")
  public ModelAndView index() {
    ModelAndView model = new ModelAndView("index");
    model.addObject("title", "We Love Coding");
    return model;
  }

  @RequestMapping(method = RequestMethod.GET,
    value = {"/debug", "/netbeans-tomcat-status-test"}
  )
  public ModelAndView debug() {
    return new ModelAndView("debug");
  }

  @RequestMapping(method = RequestMethod.GET, value = "/snoop")
  public ModelAndView snoop() {
    return new ModelAndView("snoop");
  }

  @RequestMapping(value = "/login", method = RequestMethod.GET)
  public String showLoginPage() {
    return "user/login";
  }

  @RequestMapping(value = "/signup", method = RequestMethod.GET)
  public String redirectRequestToRegistrationPage() {
    return "redirect:/user/register";
  }

}
