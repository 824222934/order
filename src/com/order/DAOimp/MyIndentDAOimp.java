package com.order.DAOimp;

import java.util.List;
import java.util.Map;

import com.order.config.FormConfig;
import com.order.dboperate.DBSearch;
import com.order.orderDAO.MyIndentDAO;

public class MyIndentDAOimp implements MyIndentDAO{

	@Override
	public List<Map<String, String>> seeIntent(String userid) {
		//�����û������ж�����Ϣ ��sql���
		String sql = "select * from indent where userid = ?";
		//���û����صļ���
		List<Map<String, String>> indentInfo = null;
		//�ø������û�id��ѯ���ݿ�õ��û���Ϣ
		indentInfo = new DBSearch().search(sql, 
				new String[]{userid}, new int[]{1,2,3,4,5,6,7},FormConfig.INDENT);
		
		return indentInfo;
		
	}

}
