package com.order.DAOimp;

import com.order.config.Constant;
import com.order.dboperate.DBInsert;
import com.order.orderDAO.RegisterDAO;

public class RegisterDAOimp implements RegisterDAO{

	public String register(String userid, String password) {
		// TODO Auto-generated method stub
		String sql = "insert user values(?,?,'','')";
		//设置用户返回的状态
		String state = new DBInsert().insertUser(sql, new String[]{userid,password});
		if(state.equals(Constant.SUCCESS))
			return Constant.SUCCESS;
		else {
			return Constant.FAIL;
		}
	}

}
