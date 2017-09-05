package springbootio.service;

import springbootio.entity.persistence.Message;
import springbootio.entity.view.ConversationList;

import java.util.List;

/**
 * Created by miaomiao on 17-9-2.
 */
public interface MessageService {

    int addMessage(Message message);

    List<Message> getConversationDetail(String conversationId, int offset, int limit);

    List<ConversationList> getConversationList(int userId, int offset, int limit);
}
