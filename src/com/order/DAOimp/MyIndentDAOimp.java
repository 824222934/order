package com.order.DAOimp;

import java.util.List;
import java.util.Map;

import com.order.config.FormConfig;
import com.order.dboperate.DBSearch;
import com.order.orderDAO.MyIndentDAO;

public class MyIndentDAOimp implements MyIndentDAO{

	@Override
	public List<Map<String, String>> seeIntent(String userid) {
		//返回用户的所有订单信息 的sql语句
		String sql = "select * from indent where userid = ?";
		//给用户返回的集合
		List<Map<String, String>> indentInfo = null;
		//用给定的用户id查询数据库得到用户信息
		indentInfo = new DBSearch().search(sql, 
				new String[]{userid}, new int[]{1,2,3,4,5,6,7},FormConfig.INDENT);
		
		return indentInfo;
		
	}

}
