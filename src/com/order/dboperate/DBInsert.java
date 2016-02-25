package com.order.dboperate;

import java.sql.ParameterMetaData;
import java.sql.SQLException;

import com.mysql.jdbc.PreparedStatement;
import com.order.config.Constant;
import com.order.datainfo.UserInfo;

public class DBInsert extends DBBase{
	
	public String insertUser(String sql,String[] params){
		/*
		 * 查找用户是否在数据库中存在
		 * 如果存在返回给用户不能注册的信息
		 */
		String sqlsearch = "select * from user where userid = ?";
		UserInfo user = (UserInfo) new DBSearch().searchUser(sqlsearch,params);

		if (user != null) {
			return Constant.FAIL;
		}
		try {
			preparedStatement = (PreparedStatement) conn.prepareStatement(sql);
			ParameterMetaData metaData = preparedStatement.getParameterMetaData();
			//得到sql中的？的个数
			int count = metaData.getParameterCount();
			//设置sql中的参数
			for (int i = 0; i < count; i++) {
				preparedStatement.setString(i+1, params[i]);
			}
			//执行插入语句
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
