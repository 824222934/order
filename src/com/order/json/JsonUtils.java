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
	 * ����û����ݲ��ҳɹ��Ļ��������û����ݲ��ҽ�result�ֶ�����Ϊ��
	 */
	public JSONObject successPacket(UserInfo userInfo) {
		// ��Result��ֵ��Ϊ1
		jsonObject.put(Constant.JSON_RESULT, Constant.SUCCESS);

		JSONObject content = new JSONObject();

		content.put(FormConfig.USER[0], userInfo.getUserid());
		content.put(FormConfig.USER[2], userInfo.getNickname());
		content.put(FormConfig.USER[3], userInfo.getPhotourl());
		jsonObject.put(Constant.JSON_CONTENT, content);

		return jsonObject;

	}

	public JSONObject successPacket(List<Map<String, String>> info) {
		// ��Result��ֵ��Ϊ1
		jsonObject.put(Constant.JSON_RESULT, Constant.SUCCESS);

		JSONArray content = new JSONArray();
		for (int i = 0; i < info.size(); i++) {
			Map<String, String> map = info.get(i);
			//�õ�info�����м�
			Set<String> set = map.keySet();
			//����info���еļ�
			Iterator<String> iterator = set.iterator();
			//���set�����л���info�ļ���һֱѭ��
			JSONObject json = new JSONObject();
			while (iterator.hasNext()) {
				//�õ���ǰ�ļ�
				String key = (String) iterator.next();
				//����jsonObject�Ķ�����
				json.put(key, map.get(key));
			}
			content.add(json);
				
		}
		
		jsonObject.put(Constant.JSON_CONTENT, content);
		
		return jsonObject;
	}

	/*
	 * ��������û�������ʧ�ܣ�ֱ�Ӹ��ͻ��˷�������Ϊ�յ�content�ֶΣ����ҽ�result�ֶ�����Ϊ��
	 */
	public JSONObject failPacket() {
		// ��result�Ľ����Ϊ0
		jsonObject.put(Constant.JSON_RESULT, Constant.FAIL);

		jsonObject.put(Constant.JSON_CONTENT, "");

		return jsonObject;

	}
}
