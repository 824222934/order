package com.order.config;

public class Constant {
	//给客户端传送的json数据的result字段的值
	public static  final String SUCCESS = "1";
	public static  final String FAIL = "0";
	
	//给客户端传送的json数据的键
	public static final String JSON_RESULT = "result";
	public static final String JSON_CONTENT = "content";
	
	/*
	 * 订单的状态
	 */
	public static final String HAVE_COMMIT = "1";//已提交
	public static final String NO_COMMIT = "2";//未提交
	public static final String HAVE_PAY = "3";//已付款
	public static final String NO_PAY = "4";//未付款
	public static final String HAVE_COMMENT = "5";//已评论
	public static final String NO_COMMENT = "6";//未评论
	
	
}
