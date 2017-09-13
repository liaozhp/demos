package com.springmvc.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by lzp on 2017/8/30.
 */
@org.springframework.stereotype.Controller
//@RequestMapping("/rest")
public class Controller {

    @RequestMapping(value = "/hello/{name}",method = RequestMethod.GET)
    public ModelAndView hello(@PathVariable String name){
        System.out.println(name);
        ModelAndView mv=new ModelAndView("success");
        mv.addObject("name",name);
//        map.addAttribute("name",name);
        return mv;
    }
}
