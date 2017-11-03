package com.jointem.hrm.utils;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.SimpleDateFormatSerializer;
import com.alibaba.fastjson.serializer.ValueFilter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

/**
 * 
 * @author htd
 * 
 */

public class JSONUtil {
	private static SerializeConfig mapping = new SerializeConfig();
	private static String dateFormat;

	static {
		dateFormat = "yyyy-MM-dd HH:mm:ss";
	}

	public JSONUtil() {
		// TODO Auto-generated constructor stub
	}

	public static Gson getGson() {
		GsonBuilder builder = new GsonBuilder().serializeNulls().setDateFormat("yyyy-MM-dd HH:mm:ss");

		Gson gson = builder.create();
		return gson;
	}

	/**
	 * 将java对象转换为json字符串
	 * 
	 * @param object
	 *            java对象
	 * 
	 * @return
	 */
	public static String createJsonString(Object value) {
		mapping.put(Date.class, new SimpleDateFormatSerializer(dateFormat));

		String str = JSON.toJSONString(value, mapping, new ValueFilter() {
			@Override
			public Object process(Object obj, String s, Object v) {
				if (v == null)
					return "";
				if (v instanceof Integer || v instanceof Long) {
					return v + "";
				}
				if (v instanceof Date) {
					SimpleDateFormat dFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					return dFormat.format(v);
				}
				return v;
			}
		}, SerializerFeature.DisableCircularReferenceDetect);// getGson().toJson(value);
		return str;
	}

	/**
	 * 将json字符串转换为对应的 java 对象
	 * 
	 * @param <T>
	 *            对象类型
	 * @param jsonString
	 *            json字符串
	 * @param cls
	 *            对象类型
	 * @return
	 */
	public static <T> T getEntity(String jsonString, Class<T> cls) {
		T t = null;
		try {
			t = JSON.parseObject(jsonString, cls);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return t;
	}

	/**
	 * 将json 字符串转为对应的对应对象的List集合
	 * 
	 * @param <T>
	 * @param jsonString
	 *            对应java对象的list集合json
	 * @param cls
	 *            java 对象类型
	 * @return
	 */
	public static <T> List<T> getEntities(String jsonString, Class<T> cls) {
		List<T> list = new ArrayList<T>();
		try {
			list = JSON.parseArray(jsonString, cls);
		} catch (Exception e) {
		}
		return list;
	}

	/**
	 * 将json 字符串转为对应的对应的Map集合
	 * 
	 * @param JsonStr
	 * @return Map
	 */
	public static Map<String, String> jsonToMap(String JsonStr) {
		Gson gson = new Gson();
		Map<String, String> infoMap = gson.fromJson(JsonStr, new TypeToken<Map<String, String>>() {
		}.getType());
		return infoMap;
	}

	public static List<Map<String, Object>> listKeyMaps(String jsonString) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		try {
			list = JSON.parseObject(jsonString, new TypeReference<List<Map<String, Object>>>() {
			});
		} catch (Exception e) {
			// TODO: handle exception
		}
		return list;
	}

	/**
	 * @param flag:判断标志
	 * @param errorTips:自定义错误提示信息
	 * @return
	 */
	public static void printCheckResultJson(boolean flag, String errorTips, HttpServletResponse response) {

		String data = null;
		// 默认不存在，显示ok,okTips默认""
		if (flag) {
			data = "{\"ok\":\"\"}";
		} else {
			data = "{\"error\":\"" + errorTips + "\"}";
		}
		try {
			response.getWriter().print(data);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	/**
	 * 把Map对象转换为JSON格式的字符串
	 * 
	 * Add by Cloudy.Y.Liu 2016-01-21
	 */
	public static String createJsonByMap(Map<String, String> map) {
		JsonObject obj = new JsonObject();
		for (Entry<String, String> entry : map.entrySet()) {
			obj.addProperty(entry.getKey(), entry.getValue());
		}
		return obj.toString();
	}
}
