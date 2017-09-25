package cn.edu.swpu.cins.studentManagement.web;

import cn.edu.swpu.cins.studentManagement.async.EventModel;
import cn.edu.swpu.cins.studentManagement.async.EventProducer;
import cn.edu.swpu.cins.studentManagement.async.EventType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import cn.edu.swpu.cins.studentManagement.entity.view.ResultData;
import cn.edu.swpu.cins.studentManagement.service.LikeService;
import cn.edu.swpu.cins.studentManagement.util.GetUsrName;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by miaomiao on 17-9-13.
 */
@RestController
@RequestMapping("/Springbootio")
public class LikeController {

    @Autowired
    GetUsrName getUsrName;
    @Autowired
    LikeService likeService;
    @Autowired
    EventProducer eventProducer;

    @PostMapping("/like")
    public ResultData like(@RequestParam String isLikeName, HttpServletRequest request){
        String username = getUsrName.AllProjects(request);
        long likeCount = likeService.like(username,isLikeName);
        eventProducer.fireEvent(new EventModel(EventType.LIKE).setActor(username).setAccept(isLikeName));
        return new ResultData(true,likeCount);
    }

    @PostMapping("/dislike")
    public ResultData dislike(@RequestParam String isLikeName, HttpServletRequest request){
        String username = getUsrName.AllProjects(request);
        long likeCount = likeService.dislike(username,isLikeName);
        return new ResultData(true,likeCount);
    }
}
