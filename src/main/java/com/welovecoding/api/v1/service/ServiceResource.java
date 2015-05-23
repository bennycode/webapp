package com.welovecoding.api.v1.service;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.Produces;


@RestController
@RequestMapping("/api/v1/service")
@Produces("application/json")
public class ServiceResource {


  @RequestMapping(value = "/ping", method = RequestMethod.GET, produces = "text/plain")
  public String ping() {
    return "alive";
  }
}
