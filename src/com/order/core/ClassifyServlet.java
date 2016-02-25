package com.order.core;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.order.DAOimp.ClassifyDAOimp;
import com.order.authorization.AuthorizationUtils;
import com.order.json.JsonUtils;

public class ClassifyServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public ClassifyServlet() {
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
		
		//�õ��û�����ĵ�����
		String shoptype = request.getParameter("type");
		//��֤��״̬
		Boolean state = new AuthorizationUtils().authorization(request, response);
		JSONObject jsonObject = new JSONObject();
		//�����֤�ɹ�
	
		if (state == true) {
			
			//�õ������ĵ��̵�Map����
			List<Map<String, String>> shop = new ClassifyDAOimp().Shop(shoptype);
			//�����ݰ�װ��json����
			jsonObject = new JsonUtils().successPacket(shop);
		
		}
		else{
			jsonObject = new JsonUtils().failPacket();
			out.print(jsonObject);
		}
		
		 out.print(jsonObject);
		
	}
	/*
	 * �û���֤
	 */
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	public void init() throws ServletException {
	}

}
