package com.welovecoding.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DefaultController {

  @RequestMapping(value = "/", method = RequestMethod.GET)
  public ModelAndView index() {
    ModelAndView model = new ModelAndView("index");
    model.addObject("msg", "hello world");
    model.setViewName("index");
    return model;
  }

  @RequestMapping(value = "/debug", method = RequestMethod.GET)
  public ModelAndView debug() {
    ModelAndView model = new ModelAndView("debug");
    model.addObject("msg", "hello world");
    model.setViewName("debug");
    return model;
  }

  @RequestMapping(value = "/snoop", method = RequestMethod.GET)
  public ModelAndView snoop() {
    ModelAndView model = new ModelAndView("snoop");
    model.addObject("msg", "hello world");
    model.setViewName("snoop");
    return model;
  }


}
