package com.order.dboperate;

import java.sql.ParameterMetaData;
import java.sql.SQLException;

import com.mysql.jdbc.PreparedStatement;
import com.order.config.Constant;
import com.order.datainfo.UserInfo;

public class DBInsert extends DBBase{
	
	public String insertUser(String sql,String[] params){
		/*
		 * �����û��Ƿ������ݿ��д���
		 * ������ڷ��ظ��û�����ע�����Ϣ
		 */
		String sqlsearch = "select * from user where userid = ?";
		UserInfo user = (UserInfo) new DBSearch().searchUser(sqlsearch,params);

		if (user != null) {
			return Constant.FAIL;
		}
		try {
			preparedStatement = (PreparedStatement) conn.prepareStatement(sql);
			ParameterMetaData metaData = preparedStatement.getParameterMetaData();
			//�õ�sql�еģ��ĸ���
			int count = metaData.getParameterCount();
			//����sql�еĲ���
			for (int i = 0; i < count; i++) {
				preparedStatement.setString(i+1, params[i]);
			}
			//ִ�в������
			preparedStatement.execute();
			
			return Constant.SUCCESS;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			super.closedb();
		}
		return Constant.FAIL;
	}
}
