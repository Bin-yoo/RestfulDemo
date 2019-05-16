package com.cn.springdemo.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cn.springdemo.entity.TblCustomer;
import com.cn.springdemo.mapper.CustomerMapper;


@Controller
public class HelloController {
	
	@Autowired
	CustomerMapper customerMapper;
 
    @RequestMapping("/hello")
    public String hello(Model m) throws Exception {
    	m.addAttribute("now", DateFormat.getDateTimeInstance().format(new Date()));
    	if(true){
    		throw new Exception("some exception");
    	}
        return "hello";
    }
    
    @RequestMapping("/listCustomer")
    public String listCategory(Model m) throws Exception {
        List<TblCustomer> cs = customerMapper.selectAll();
          
        m.addAttribute("cs", cs);
          
        return "listCustomer";
    }
      

}
