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

import com.order.DAOimp.MyIndentDAOimp;
import com.order.authorization.AuthorizationUtils;
import com.order.json.JsonUtils;
import com.order.orderDAO.MyIndentDAO;

public class MyIndentServlet extends HttpServlet {

	/**
	 * 用户查看我的订单
	 */
	private static final long serialVersionUID = 1L;

	public MyIndentServlet() {
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
		//从容客户端获得用户id
		String userid = request.getParameter("userid");
		AuthorizationUtils authorizationUtils = new AuthorizationUtils();
		Boolean state = authorizationUtils.authorization(request, response);
		
		//如果认证成功
		if(state == true){
			MyIndentDAO indentDAO = new MyIndentDAOimp();
			//得到订单信息
			List<Map<String, String>> indentInfo = indentDAO.seeIntent(userid);
			//将数据包装成json数据
			JSONObject jsonObject = new JsonUtils().successPacket(indentInfo);
			out.print(jsonObject);
		}
		else{
			JSONObject jsonObject = new JsonUtils().failPacket();
			out.print(jsonObject);
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	public void init() throws ServletException {

	}

}
