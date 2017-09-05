package springbootio.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springbootio.dao.MessageDao;
import springbootio.entity.persistence.Message;
import springbootio.entity.view.ConversationList;
import springbootio.service.MessageService;

import java.util.List;

/**
 * Created by miaomiao on 17-9-2.
 */
@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    MessageDao messageDao;

    @Override
    public int addMessage(Message message) {
        return messageDao.addMessage(message);
    }

    public List<Message> getConversationDetail(String conversationId,int offset,int limit){
        return messageDao.getConversationDetail(conversationId,offset,limit);
    }

    @Override
    public List<ConversationList> getConversationList(int userId, int offset, int limit) {
        return messageDao.getConversationList(userId,offset,limit);
    }
}
