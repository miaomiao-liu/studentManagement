package springbootio.web;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import springbootio.entity.persistence.Message;
import springbootio.entity.view.ConversationList;
import springbootio.entity.view.ResultData;
import springbootio.service.MessageService;
import java.util.Date;
import java.util.List;

/**
 * Created by miaomiao on 17-9-2.
 */
@RestController
@RequestMapping("/Springbootio/msg")
public class MessageController {

    @Autowired
    MessageService messageService;

    @RequestMapping(path = {"/addMessage"},method = {RequestMethod.POST})
    public ResultData addMessage(@RequestParam("fromId") int fromId,
                                 @RequestParam("toId") int toId,
                                 @RequestParam("content") String content){
        try {
            Message msg = new Message();
            msg.setContent(content);
            msg.setFromId(fromId);
            msg.setToId(toId);
            msg.setCreateDate(new Date());
            msg.setConversationId(fromId < toId ? String.format("%d_%d",fromId, toId) : String.format("%d_%d",toId, fromId));
            messageService.addMessage(msg);
            return new ResultData(true,msg);
        }catch (Exception e){
            return new ResultData(false,e.getMessage());
        }
    }

    //查询与某一个人的对话
    @RequestMapping(path = {"/conversationDetail/{conversationId}"},method = {RequestMethod.GET})
    public ResultData conversationDetal(@PathVariable("conversationId") String conversationId) {
        try {
            List<Message> conversationList = messageService.getConversationDetail(conversationId, 0, 10);
            return new ResultData(true,conversationList);
        }catch(Exception e){
            return new ResultData(false,e.getMessage());
        }
    }

    //查询对话列表
    @RequestMapping(path = {"/conversationList/{userId}"},method = {RequestMethod.GET})
    public ResultData conversationList(@PathVariable("userId") int userId){
        try{
            List<ConversationList> conversationList = messageService.getConversationList(userId, 0, 10);
            return new ResultData(true,conversationList);

        }catch (Exception e){
            return new ResultData(false,e.getMessage());

        }
    }
}
