package springbootio.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springbootio.entity.view.ResultData;
import springbootio.service.LikeService;
import springbootio.util.GetUsrName;

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

    @PostMapping("/like")
    public ResultData like(@RequestParam String isLikeName, HttpServletRequest request){
        String username = getUsrName.AllProjects(request);
        long likeCount = likeService.like(username,isLikeName);
        return new ResultData(true,likeCount);
    }

    @PostMapping("/dislike")
    public ResultData dislike(@RequestParam String isLikeName, HttpServletRequest request){
        String username = getUsrName.AllProjects(request);
        long likeCount = likeService.dislike(username,isLikeName);
        return new ResultData(true,likeCount);
    }
}
