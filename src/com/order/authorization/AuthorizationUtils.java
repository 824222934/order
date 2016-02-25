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
		 * ��֤�ӿͻ��˴�����session
		 */
		public Boolean authorization(HttpServletRequest request,HttpServletResponse response){
			String authentication = request.getHeader("Authorization");
			String idandpass = null;
			
			if (authentication==null||authentication == "") {
				//������֤ʧ�ܵ�״̬401
				response.setStatus(401);
				response.setHeader("WWW-Authorization", "Basic realm=\"����������\"");
				return false;
			}
			try {
				//��Base64���ܿͻ��˴�����Authorizationͷ��Ϣ
				idandpass = new String(new BASE64Decoder().decodeBuffer(authentication));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//���õ��û�id���������ʵ��Ϣ���û��������룩
			String id = idandpass.split(":")[0];
			String pass = idandpass.split(":")[1];
			//�����ݿ��в����Ƿ��û�������ʵ����
			String sql = "select password from user where userid = ?";
			@SuppressWarnings("unchecked")
			Map<String, String> realpasword = (Map<String, String>) new DBSearch().search(sql, 
					new String[]{id}, new int[]{2},FormConfig.USER);
			//����������û��������û���ʵ�������
			if (realpasword.get(FormConfig.USER[1]).equals(pass)) {
				//���û�������Ӧ����Դ
				
				return true;
			}
			//����������û��������û�ʵ�����벻���
			else {
				response.setStatus(401);
				response.setHeader("WWW-Authorization", "Basic realm=\"����������\"");
				return false;
			}
		}

}
