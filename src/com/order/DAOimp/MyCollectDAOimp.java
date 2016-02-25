package com.order.DAOimp;

import java.util.List;
import java.util.Map;

import com.order.config.FormConfig;
import com.order.dboperate.DBSearch;
import com.order.orderDAO.MyCollectDAO;


public class MyCollectDAOimp implements MyCollectDAO{
	
	@Override
	public List<Map<String, String>> seeCollect(String userid) {
		String sql = "select seller.* from user,seller,collection where "
				+ "collection.userid=? and seller.sellerid=collection.sellerid";
		
		List<Map<String, String>> info= null;
		
		info = new DBSearch().search(sql, new String[]{userid},
				new int[]{1,2,3,4,5,6,7}, FormConfig.SELLER);
		return info;
	}

	
}
