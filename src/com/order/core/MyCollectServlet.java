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

import com.order.DAOimp.MyCollectDAOimp;
import com.order.json.JsonUtils;
import com.order.orderDAO.MyCollectDAO;

public class MyCollectServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	public MyCollectServlet() {
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
		
		String userid = request.getParameter("userid");
		//认证用户
		/*AuthorizationUtils authorizationUtils = new AuthorizationUtils();
		Boolean state = authorizationUtils.authorization(request, response);*/
		
			MyCollectDAO myCollectDAO = new MyCollectDAOimp();
			List<Map<String, String>> sellerMap = myCollectDAO.seeCollect(userid);
			JSONObject jsonObject = new JsonUtils().successPacket(sellerMap);
			out.print(jsonObject);
			
		
		
		
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
	
	public void init() throws ServletException {
		// Put your code here
	}

}
