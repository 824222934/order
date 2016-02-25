package com.order.dboperate;

import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.ResultSet;
import com.order.dbutils.DBtools;

public class DBBase {
	
	ResultSet rsResultSet = null;
	PreparedStatement preparedStatement = null;
	DBtools tools = new DBtools();
	Connection conn = tools.getConnection();
	
	/*
	 * 关闭数据库的各项链接
	 */
	public void closedb(){
		if (rsResultSet != null) {
			try {
				rsResultSet.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			rsResultSet = null;
		}
		if(preparedStatement != null){
			try {
				preparedStatement.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		tools.closeConnection(conn);
	}
}
