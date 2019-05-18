package com.cn.springdemo.biz;

import java.util.List;

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
			System.out.println("注册失败");
			e.printStackTrace();
			
			return false;
		}
	}

	public List<TblCustomer> allCustomer() {
		return customerMapper.selectAll();
	}

	public boolean remove(Integer id) {
		try {
			customerMapper.deleteFun(id);
			
			return true;
		} catch (Exception e) {
			System.out.println("删除失败!!");
			e.printStackTrace();
			
			return false;
		}
	}
	
	public boolean modifyFun(TblCustomer tblCustomer) {
		try{
			customerMapper.updateFun(tblCustomer);
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
}
