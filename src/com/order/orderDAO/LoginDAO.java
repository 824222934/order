package com.order.orderDAO;

import com.order.datainfo.UserInfo;


public interface LoginDAO {
	
	/*
	 * �û���¼����
	 */
	public UserInfo Login(String userid,String password);
}
