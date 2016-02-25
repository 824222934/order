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
		
		//得到用户点击的店铺名
		String shoptype = request.getParameter("type");
		//认证的状态
		Boolean state = new AuthorizationUtils().authorization(request, response);
		JSONObject jsonObject = new JSONObject();
		//如果认证成功
	
		if (state == true) {
			
			//得到搜索的店铺的Map集合
			List<Map<String, String>> shop = new ClassifyDAOimp().Shop(shoptype);
			//将数据包装成json数据
			jsonObject = new JsonUtils().successPacket(shop);
		
		}
		else{
			jsonObject = new JsonUtils().failPacket();
			out.print(jsonObject);
		}
		
		 out.print(jsonObject);
		
	}
	/*
	 * 用户认证
	 */
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	public void init() throws ServletException {
	}

}
