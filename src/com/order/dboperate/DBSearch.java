package com.order.dboperate;

import java.sql.ParameterMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.ResultSet;
import com.order.datainfo.UserInfo;

public class DBSearch extends DBBase{
	
	/*
	 *  根据sql语句和数组参数在数据库user表中查询数据
	 */
	public Object searchUser(String sql,String[] params){
		/*
		 * 返回用户对象
		 */
		try {
			preparedStatement = (PreparedStatement) conn.prepareStatement(sql);
			ParameterMetaData metaData = preparedStatement.getParameterMetaData();
			int count = metaData.getParameterCount();
			for (int i = 0; i < count; i++) {
				preparedStatement.setString(i+1, params[i]);
			}
			rsResultSet = (ResultSet) preparedStatement.executeQuery();
			UserInfo userInfo = new UserInfo();
			if(rsResultSet.next()){
				String useridString = rsResultSet.getString(1);
				String nicknameString = rsResultSet.getString(3);
				String photourlString = rsResultSet.getString(4);
				
				userInfo.setUserid(useridString);
				userInfo.setNickname(nicknameString);
				userInfo.setPhotourl(photourlString);
			}
			else{
				return null;
			}
			return userInfo;
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			//各种连接
			super.closedb();
		}
		return null;
	}
	/*返回用户的局部信息数组
	 * sql:查询的sql语句
	 * param1 ：传进来的用户sql中？的信息
	 * param2：需要得到的用户的信息在数据库中的位置
	 * form :查询所用到的表。
	 */
	public List<Map<String, String>> search(String sql,String[] param1,int[] param2,String[] form){
		
		try {
			List<Map<String,String>> info= new ArrayList<Map<String,String>>();

			preparedStatement = (PreparedStatement) conn.prepareStatement(sql);
			ParameterMetaData metaData = preparedStatement.getParameterMetaData();
			int count = metaData.getParameterCount();
			for (int i = 0; i < count; i++) {
				preparedStatement.setString(i+1, param1[i]);
			}
			rsResultSet = (ResultSet) preparedStatement.executeQuery();
			while(rsResultSet.next()){
				Map<String, String> map = new HashMap<String, String>();
				for (int i : param2) {
					map.put(form[i-1], rsResultSet.getString(i));
				}
				info.add(map);
				//String useridString = rsResultSet.getString(2);
				
			}
			return info;
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			//各种连接
			super.closedb();
		}
		return null;
	}
}
