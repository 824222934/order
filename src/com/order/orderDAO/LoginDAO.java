package com.order.orderDAO;

import com.order.datainfo.UserInfo;


public interface LoginDAO {
	
	/*
	 * 用户登录方法
	 */
	public UserInfo Login(String userid,String password);
}
