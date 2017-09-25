package cn.edu.swpu.cins.studentManagement.util;

/**
 * Created by miaomiao on 17-9-12.
 */
public class RedisKeyUtil {

    private static String SPLL = ":";
    private static String BIZ_LIKE = "LIKE";
    private static String  BIZ_DISLIKE = "DISLIKE";

    private static String BIZ_EVENTQUEUE = "EVENT_QUEUE";


    public static String getLikeKey(String isLikeName) {
        return BIZ_LIKE + SPLL + isLikeName;
    }

    public static String getDislikeKey(String isLikeName){
        return  BIZ_DISLIKE + SPLL + isLikeName;
    }

    public static String getBizEventqueue() {
        return BIZ_EVENTQUEUE;
    }
}
