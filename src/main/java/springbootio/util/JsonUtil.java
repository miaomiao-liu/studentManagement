package springbootio.util;

import com.alibaba.fastjson.JSONObject;

import java.util.Map;

/**
 * 将对象转换为json
 * Created by miaomiao on 17-9-2.
 */
public class JsonUtil {

    public static String getJSONString(int code){
        JSONObject json = new JSONObject();
        json.put("code",code);
        return json.toJSONString();
    }

    public static String getJSONString(int code, Map<String, Object> map){
        JSONObject json = new JSONObject();
        json.put("code",code);
        for (Map.Entry<String, Object> entry : map.entrySet()){
            json.put(entry.getKey(),entry.getValue());
        }
        return json.toJSONString();
    }
}
