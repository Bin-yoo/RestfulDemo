package com.cn.springdemo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.cn.springdemo.entity.TblCustomer;

@Mapper
public interface CustomerMapper {

	List<TblCustomer> selectAll();
	
	TblCustomer selectByCustomerName(String userName);

	void insertFun(TblCustomer tblCustomer);
	
}
