package springbootio.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springbootio.service.LikeService;
import springbootio.util.JedisAdapter;
import springbootio.util.RedisKeyUtil;

/**
 * Created by miaomiao on 17-9-12.
 */
@Service
public class LikeServiceImpl implements LikeService {

    @Autowired
    JedisAdapter jedisAdapter;


    @Override
    public int getLikeStatus(String username,String isLikeName) {
        String like = RedisKeyUtil.getLikeKey(isLikeName);
        if(jedisAdapter.sismember(like,username)){
            return 1;
        }
        String dislike = RedisKeyUtil.getDislikeKey(isLikeName);
        return jedisAdapter.sismember(dislike,username) ? -1 : 0;
    }

    @Override
    public long like(String username,String isLikeName) {
        String like = RedisKeyUtil.getLikeKey(isLikeName);
        jedisAdapter.sadd(like, username);

        String dislike = RedisKeyUtil.getDislikeKey(isLikeName);
        jedisAdapter.srem(dislike,username);

        return jedisAdapter.scard(like);
    }

    @Override
    public long dislike(String username,String isLikeName) {
        String like = RedisKeyUtil.getLikeKey(isLikeName);
        jedisAdapter.srem(like, username);

        String dislike = RedisKeyUtil.getDislikeKey(isLikeName);
        jedisAdapter.sadd(dislike,username);

        return jedisAdapter.scard(like);

    }
}
