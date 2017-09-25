package cn.edu.swpu.cins.studentManagement.util;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.List;

/**
 * Created by miaomiao on 17-9-6.
 */
@Service
public class JedisAdapter implements InitializingBean{
    private static final Logger logger = LoggerFactory.getLogger(JedisAdapter.class);

    private JedisPool pool = null;


    @Override
    public void afterPropertiesSet() throws Exception {
        pool = new JedisPool("localhost",6379);
    }

    public Jedis getPool() {
        return pool.getResource();
    }

    public long sadd (String key,String value){
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            return jedis.sadd(key,value);
        }catch (Exception e){
            logger.error("发生异常"+e.getMessage());
            return 0;
        }finally {
            if(jedis != null){
                jedis.close();
            }
        }
    }

    public long srem (String key,String value){
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            return jedis.srem(key,value);
        }catch (Exception e){
            logger.error("发生异常"+e.getMessage());
            return 0;
        }finally {
            if(jedis != null){
                jedis.close();
            }
        }
    }


    public boolean  sismember(String key,String value){
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            return jedis.sismember(key,value);
        }catch (Exception e){
            logger.error("发生异常"+e.getMessage());
            return false;
        }finally {
            if(jedis != null){
                jedis.close();
            }
        }
    }

    public long scard(String key){
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            return jedis.scard(key);
        }catch (Exception e){
            logger.error("发生异常"+e.getMessage());
            return 0;
        }finally {
            if(jedis != null){
                jedis.close();
            }
        }
    }

    //保存一个对象 将对象转化为JSON 串
    public void setObject(String key,Object obj){
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            jedis.set(key, JSON.toJSONString(obj));
        }catch (Exception e){
            logger.error("发生异常"+e.getMessage());
        }finally {
            if(jedis != null){
                jedis.close();
            }
        }
    }

    public <T> T getObject(String key, Class<T> clazz){
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            String value = jedis.get(key);
            if(value != null){
                return JSON.parseObject(value,clazz);
            }

        }catch (Exception e){
            logger.error("发生异常"+e.getMessage());
        }finally {
            if(jedis != null){
                jedis.close();
            }
        }
        return null;
    }

    //消息队列
    public void lpush(String key,String value){
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            jedis.lpush(key, value);
        }catch (Exception e){
            logger.error("发生异常"+e.getMessage());
        }finally {
            if(jedis != null){
                jedis.close();
            }
        }
    }

    //取出队列
    public List<String> brpop(int timeout, String key) {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            return jedis.brpop(timeout, key);
        } catch (Exception e) {
            logger.error("发生异常" + e.getMessage());
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return null;
    }


}
