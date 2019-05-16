package com.cn.springdemo.biz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cn.springdemo.entity.TblCustomer;
import com.cn.springdemo.mapper.CustomerMapper;

@Service
public class TblCustomerBiz {

	@Autowired
	CustomerMapper customerMapper;

	public TblCustomer login(String userName,String password){
		TblCustomer tblCustomer = customerMapper.selectByCustomerName(userName);
		
		if (tblCustomer != null && tblCustomer.getPassword().equals(password)){
			return tblCustomer;
		}else{
			return null;
		}
	}
	
	public boolean register(TblCustomer tblCustomer){
		try {
			customerMapper.insertFun(tblCustomer);
			
			return true;
		} catch (Exception e) {
			System.out.println("ע��ʧ��!!!");
			e.printStackTrace();
			
			return false;
		}
	}
	
}
