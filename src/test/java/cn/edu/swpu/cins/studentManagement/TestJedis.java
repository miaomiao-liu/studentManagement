package cn.edu.swpu.cins.studentManagement;

import cn.edu.swpu.cins.studentManagement.entity.persistence.StudentInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import cn.edu.swpu.cins.studentManagement.entity.persistence.StudentInfo;
import cn.edu.swpu.cins.studentManagement.util.JedisAdapter;

/**
 * Created by miaomiao on 17-9-24.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestJedis {

    @Autowired
    JedisAdapter jedisAdapter;

    @Test
    public void testObject(){
        StudentInfo studentInfo = new StudentInfo();
        studentInfo.setUsername("miaomiao");
        studentInfo.setPassword("123456");
        studentInfo.setEmail("36899");

        jedisAdapter.setObject("stu1",studentInfo);

        StudentInfo stu = jedisAdapter.getObject("stu1",studentInfo.getClass());
        System.out.println(stu);

    }

}
