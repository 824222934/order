package com.order.DAOimp;

import com.order.datainfo.UserInfo;
import com.order.dboperate.DBSearch;
import com.order.orderDAO.LoginDAO;

public class LoginDAOimp implements  LoginDAO{

	public UserInfo Login(String userid, String password) {
		
		UserInfo userInfo = null;
		String sql = "select * from user where userid = ? and password = ?";
		userInfo =  (UserInfo) new DBSearch().searchUser(sql, new String[]{ userid,password });
		
		return userInfo;
	}

}
