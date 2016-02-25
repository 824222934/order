package com.order.json;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.order.config.Constant;
import com.order.config.FormConfig;
import com.order.datainfo.UserInfo;

public class JsonUtils {
	JSONObject jsonObject = new JSONObject();

	/*
	 * 如果用户数据查找成功的话，返回用户数据并且将result字段设置为真
	 */
	public JSONObject successPacket(UserInfo userInfo) {
		// 将Result的值置为1
		jsonObject.put(Constant.JSON_RESULT, Constant.SUCCESS);

		JSONObject content = new JSONObject();

		content.put(FormConfig.USER[0], userInfo.getUserid());
		content.put(FormConfig.USER[2], userInfo.getNickname());
		content.put(FormConfig.USER[3], userInfo.getPhotourl());
		jsonObject.put(Constant.JSON_CONTENT, content);

		return jsonObject;

	}

	public JSONObject successPacket(List<Map<String, String>> info) {
		// 将Result的值置为1
		jsonObject.put(Constant.JSON_RESULT, Constant.SUCCESS);

		JSONArray content = new JSONArray();
		for (int i = 0; i < info.size(); i++) {
			Map<String, String> map = info.get(i);
			//得到info的所有键
			Set<String> set = map.keySet();
			//遍历info所有的键
			Iterator<String> iterator = set.iterator();
			//如果set集合中还有info的键，一直循环
			JSONObject json = new JSONObject();
			while (iterator.hasNext()) {
				//得到当前的键
				String key = (String) iterator.next();
				//放入jsonObject的对象中
				json.put(key, map.get(key));
			}
			content.add(json);
				
		}
		
		jsonObject.put(Constant.JSON_CONTENT, content);
		
		return jsonObject;
	}

	/*
	 * 如果查找用户的数据失败，直接给客户端返回内容为空的content字段，并且将result字段设置为假
	 */
	public JSONObject failPacket() {
		// 将result的结果置为0
		jsonObject.put(Constant.JSON_RESULT, Constant.FAIL);

		jsonObject.put(Constant.JSON_CONTENT, "");

		return jsonObject;

	}
}
