package com.order.DAOimp;

import java.util.List;
import java.util.Map;

import com.order.config.FormConfig;
import com.order.dboperate.DBSearch;
import com.order.orderDAO.ClassifyDAO;

public class ClassifyDAOimp implements ClassifyDAO{

	@Override
	public List<Map<String, String>> Shop(String shoptype) {
			String sql = "select * from seller where type = ?";
			List<Map<String, String>> info = null;
			info = new DBSearch().search(sql, new String[]{shoptype},
					new int[]{1,2,3,4,5,6}, FormConfig.SELLER);
		return info;
	}

	

}
