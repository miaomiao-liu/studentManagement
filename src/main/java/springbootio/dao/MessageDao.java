package springbootio.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import springbootio.entity.persistence.Message;
import springbootio.entity.view.ConversationList;

import java.util.List;

/**
 * Created by miaomiao on 17-9-2.
 */
@Mapper
@Repository
public interface MessageDao {

    String TABLE_NAME = "message";
    String INSERT_FIELDS = "from_id,to_id,content,create_date,has_read,conversation_id ";
    String SELECT_FIELDS = "id, "+INSERT_FIELDS;

    @Insert({"insert into ",TABLE_NAME,"(",INSERT_FIELDS,
            ") values (#{fromId},#{toId},#{content},#{createDate},#{hasRead},#{conversationId})"})
    int addMessage(Message message);


    @Select({"select ", SELECT_FIELDS ,"from ", TABLE_NAME,
            " where conversation_id = #{conversationId} order by id desc limit #{offset},#{limit}"})
    List<Message> getConversationDetail(@Param("conversationId") String conversationId,
                                        @Param("offset") int offset,@Param("limit") int limit);

    //没有显示最新的
    @Select({"select ", INSERT_FIELDS," ,count(id) as cnt from ( select * from ", TABLE_NAME," where from_id=#{userId} or to_id=#{userId} order by id desc) total group by conversation_id order by id desc limit #{offset},#{limit}"})
    List<ConversationList> getConversationList(@Param("userId") int userId,
                                               @Param("offset") int offset, @Param("limit") int limit);

}
