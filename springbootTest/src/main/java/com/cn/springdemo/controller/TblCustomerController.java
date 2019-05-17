package com.cn.springdemo.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cn.springdemo.biz.TblCustomerBiz;
import com.cn.springdemo.entity.TblCustomer;

@RestController
@CrossOrigin(origins = "http://localhost:8080", maxAge = 3600)
public class TblCustomerController {
	
	@Autowired
	TblCustomerBiz tblCustomerBiz;
	
	@GetMapping("/login")
	public String Login(String userName,String password){
		
		TblCustomer tblCustomer = tblCustomerBiz.login(userName, password);
		
		if(tblCustomer == null){
			return "登录成功";
		}
		
		return "登录失败";
	}
	
	@PostMapping("/register")
	public String Register(@RequestBody TblCustomer tblCustomer){
		
		boolean flag = tblCustomerBiz.register(tblCustomer);
		
		if(flag){
			return "注册成功";
		}else{
			return "注册失败";
		}
	}
	
	@GetMapping("/customer")
	public List<TblCustomer> customerList(){
		
		List<TblCustomer> list = tblCustomerBiz.allCustomer();
		
		if(list == null){
			return null;
		}
		
		return list;
	}
	
	@DeleteMapping("/customer/{id}")
	public String removeCustomer(@PathVariable Integer id){
		System.out.println(id);
		boolean flag = tblCustomerBiz.remove(id);
		
		if(flag){
			return "删除成功";
		}else{
			return "删除失败";
		}
	}
}
