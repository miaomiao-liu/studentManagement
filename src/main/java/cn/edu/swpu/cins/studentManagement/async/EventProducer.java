package cn.edu.swpu.cins.studentManagement.async;

import cn.edu.swpu.cins.studentManagement.util.JedisAdapter;
import cn.edu.swpu.cins.studentManagement.util.RedisKeyUtil;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by miaomiao on 17-9-25.
 */
@Service
public class EventProducer {

    @Autowired
    JedisAdapter jedisAdapter;

    public boolean fireEvent(EventModel eventModel){
        try {
            String json = JSON.toJSONString(eventModel);
            String key = RedisKeyUtil.getBizEventqueue();
            jedisAdapter.lpush(key, json);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
