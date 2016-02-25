package com.order.orderDAO;

import java.util.List;
import java.util.Map;

public interface MyIndentDAO {
	/*
	 * 用户查看“我的订单”的接口
	 */
	public List<Map<String, String>> seeIntent(String userid);
}
