package com.cn.springdemo.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.cn.springdemo.Application;
import com.cn.springdemo.entity.TblCustomer;
import com.cn.springdemo.mapper.CustomerMapper;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class TestJpa {
	
	@Autowired CustomerMapper customerMapper;
	
	@Test
	public void test(){
		List<TblCustomer> list = customerMapper.selectAll();
		for (TblCustomer c : list) {
            System.out.println("c.getCustomerName():"+ c.getCustomerName());
        }
	}

}