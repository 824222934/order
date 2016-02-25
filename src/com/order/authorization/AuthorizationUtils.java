package com.order.authorization;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sun.misc.BASE64Decoder;

import com.order.config.FormConfig;
import com.order.dboperate.DBSearch;

@SuppressWarnings("restriction")
public class AuthorizationUtils {

		/*
		 * 认证从客户端传来的session
		 */
		public Boolean authorization(HttpServletRequest request,HttpServletResponse response){
			String authentication = request.getHeader("Authorization");
			String idandpass = null;
			
			if (authentication==null||authentication == "") {
				//发送认证失败的状态401
				response.setStatus(401);
				response.setHeader("WWW-Authorization", "Basic realm=\"请输入密码\"");
				return false;
			}
			try {
				//用Base64解密客户端传来的Authorization头信息
				idandpass = new String(new BASE64Decoder().decodeBuffer(authentication));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//，得到用户id和密码的真实信息（用户名：密码）
			String id = idandpass.split(":")[0];
			String pass = idandpass.split(":")[1];
			//在数据库中查找是否用户名的真实密码
			String sql = "select password from user where userid = ?";
			@SuppressWarnings("unchecked")
			Map<String, String> realpasword = (Map<String, String>) new DBSearch().search(sql, 
					new String[]{id}, new int[]{2},FormConfig.USER);
			//如果传来的用户密码与用户真实密码相符
			if (realpasword.get(FormConfig.USER[1]).equals(pass)) {
				//给用户返回相应的资源
				
				return true;
			}
			//如果传来的用户密码与用户实际密码不相符
			else {
				response.setStatus(401);
				response.setHeader("WWW-Authorization", "Basic realm=\"请输入密码\"");
				return false;
			}
		}

}
