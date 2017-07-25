package onther;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class JSONUtil {
	/**
	 * 
	 * @param jsonData	需要处理的json字符串
	 * @param key
	 * @param value
	 * @return
	 */
	public static Object paserJSON(String jsonData,String key,Object value){
	    GsonBuilder builder = new GsonBuilder();
        // 不转换没有 @Expose 注解的字段
        builder.excludeFieldsWithoutExposeAnnotation();
        Gson gson = builder.create();
        
        try {
        	jsonData=java.net.URLDecoder.decode(jsonData,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			
		}
        //将jsonData数据转换成HashMap对象
        HashMap  obj=   gson.fromJson(jsonData, HashMap.class); 
        obj.put(key, value);
        Object json=gson.toJson(obj);
        return json;
	}
	/**
	 * 垂直向下查找files
	 * 
	 * @param jsonObject
	 * @param field
	 * @return
	 */
	public static Map<String, Object> recursionFind(JSONObject jsonObject,
			String field,Map<String, Object> result) {
		Set<String> keys = jsonObject.keySet();
		for (String key : keys) {
			//System.out.println(key);
			if (key.equals(field)) { // 如果找到了
				result.put(key, jsonObject.get(key));
				//System.out.println(jsonObject.get(key).toString());
				break;
			}
			// 没有找到，进入JSON子对象
			Object object = jsonObject.get(key);
			if (object == null ||object.toString().equals("") || object.toString().equals("null"))
				continue;
			if (object instanceof JSONObject)
				recursionFind((JSONObject) object, field,result);
			if (object instanceof JSONArray) {
				String jsonArray = object.toString();
				String jsonStr = jsonArray.replace("[", "").replace("]", "");
				if(jsonStr.equals(""))
					continue;
				JSONObject children = JSONObject.fromObject(jsonStr);
				recursionFind(children, field,result);
			}
		}
		return result;
	}

	/**
	 * 获取一条记录
	 * 
	 * @param jsonArray
	 * @param fields
	 * @return
	 */
	public static Map<String, Object> record(JSONObject jsonObject,
			String fields) {
		Map<String, Object> onrRecord = new HashMap<String, Object>();
		String[] fieldArray={""};
		if(fields.contains(",")){
			fieldArray = fields.split(",");
		}else{
			fieldArray[0]=fields;
		}
		for (int i = 0; i < fieldArray.length; i++) {
			Map<String, Object> tempMap = recursionFind(jsonObject,fieldArray[i],onrRecord);
			if(tempMap==null)
				continue;
			Set<String> keys = tempMap.keySet();
			for (String key : keys) {
				onrRecord.put(key, tempMap.get(key));
			}
		}
		//System.out.println(onrRecord);
		return onrRecord;
	}

	/**
	 * 返回结果
	 * 
	 * @param jsonArray
	 * @param fields
	 * @return
	 */
	public static List<Map<String, Object>> result(JSONArray jsonArray,
			String fields) {
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < jsonArray.size(); i++) {
			Map<String, Object> record = record((JSONObject) jsonArray.get(i),
					fields);
			result.add(record);
		}
		System.out.println(result);
		return result;
	}
	
	/**
	 * 将easyui的Json对象数据转化为实体(List)对象
	 * 
	 * @param jsonData
	 * @return
	 */
	public static List<HashMap<String, Object>> easyuiJsonToEntity(String jsonData) {
		GsonBuilder builder = new GsonBuilder();
		// 不转换没有 @Expose 注解的字段
		builder.excludeFieldsWithoutExposeAnnotation();
		Gson gson = builder.create();

		try {
			jsonData = java.net.URLDecoder.decode(jsonData, "UTF-8");
		} catch (UnsupportedEncodingException e) {

		}
		// 将jsonData数据转换成HashMap对象
		HashMap obj = gson.fromJson(jsonData, HashMap.class);
		System.out.println("Test Out:" + obj);
		List<HashMap<String, Object>> rs = new ArrayList<HashMap<String, Object>>();
		try {
			List<Object> list = (List<Object>) obj.get("rows");
			if (list == null || list.size() <= 0) { // 只有一行
				rs.add(obj);
			} else {
				for (Object str : list) {
					if (str != null) {
						String jsonStr = gson.toJson(str);
						HashMap rsMap = gson.fromJson(jsonStr, HashMap.class);

						if (rsMap != null && rsMap.size() > 0) {
							rs.add(rsMap);
						}
					}
				}
			}
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return rs;
	}
}
