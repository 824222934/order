package com.order.core;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.order.DAOimp.LoginDAOimp;
import com.order.datainfo.UserInfo;
import com.order.json.JsonUtils;
import com.order.orderDAO.LoginDAO;

public class LoginServlet extends HttpServlet {
	/*
	 * ��¼ҳ��
	 */
	private static final long serialVersionUID = 1L;

	public LoginServlet() {
		super();
	}

	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html; charset=gbk");   
		response.setCharacterEncoding("utf-8"); 
		PrintWriter out = response.getWriter();
		
		UserInfo user = null;
		JSONObject jsonObject = null;
		//����û������Ĳ���
		String userid = request.getParameter("userid");
		String password = request.getParameter("password");
		
		LoginDAO login = new LoginDAOimp();
		user = login.Login(userid, password);
		//�û���½�ɹ�
		if(user != null){
			//����session�ֶ�
			 request.getSession();
			//��json���ݴ��͸��ͻ���
			jsonObject = new JsonUtils().successPacket(user);	
			
		}
		//�û���¼ʧ��
		else{
			//��json���ݴ��͸��ͻ���
			jsonObject = new JsonUtils().failPacket();
		}
		out.print(jsonObject);
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	 }

	public void init() throws ServletException {
	}

}
