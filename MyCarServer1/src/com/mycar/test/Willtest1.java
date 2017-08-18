package com.mycar.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.mycar.util.ToolUtil;

public class Willtest1 {

	@Test
	public void test() {
		System.out.println("will test 1");
		String rtn = ToolUtil.getProConfig("tb.api.apisecret");
		System.out.println("rtn:" + rtn);
	}
	
	@Test
	public void test1() {
		System.out.println("will test 2");
	}	

}
