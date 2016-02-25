package com.order.core;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.order.DAOimp.LoginDAOimp;
import com.order.DAOimp.RegisterDAOimp;
import com.order.config.Constant;
import com.order.datainfo.UserInfo;
import com.order.json.JsonUtils;
import com.order.orderDAO.LoginDAO;
import com.order.orderDAO.RegisterDAO;

public class RegisterServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	public RegisterServlet() {
		super();
	}

	
	public void destroy() {
		super.destroy(); 
	}


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		
		response.setContentType("text/html; charset=gbk");   
		response.setCharacterEncoding("utf-8"); 
		PrintWriter out = response.getWriter();
		
		JSONObject jsonObject = null;
		UserInfo user = null;
		
		String userid = request.getParameter("userid");
		String password = request.getParameter("password");
		
		//�õ�ע�����Ķ���	
		RegisterDAO registerDAO = new RegisterDAOimp();
		//�����û����ص�״̬
		String stateString = registerDAO.register(userid, password);
		//����û�ע��ɹ�
		if(stateString.equals(Constant.SUCCESS)){
			//�û�ע��ɹ��Զ���¼�����ҷ����û���Ϣ
			LoginDAO loginDAO = new LoginDAOimp();
			user = loginDAO.Login(userid, password);
			//����session�ֶ�
			request.getSession();
			//��json���ݴ��͸��ͻ���
			jsonObject = new JsonUtils().successPacket(user);	
		}
		//����û���¼ʧ��
		else {
			jsonObject = new JsonUtils().failPacket();
		}
		out.print(jsonObject);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	
	public void init() throws ServletException {
		// Put your code here
	}

}
