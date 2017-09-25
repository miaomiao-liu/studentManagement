package cn.edu.swpu.cins.studentManagement.service;

/**
 * Created by miaomiao on 17-9-12.
 */

public interface LikeService {

    int getLikeStatus(String username,String isLikeName);

    long like(String username,String isLikeName);

    long dislike(String username,String isLikeName);
}
