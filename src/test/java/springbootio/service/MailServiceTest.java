package springbootio.service;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.context.junit4.SpringRunner;




/**
 * Created by muyi on 17-4-22.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MailServiceTest{



    private String to = "yangquan95@163.com";

    @Autowired
    private MailService mailService;

    @Test
    public void sendSimpleMail() throws Exception{


//       mailService.sendSimpleMail(to, "主题：注册验证码");

       System.out.print(mailService.sendSimpleMail(to, "主题：注册验证码"));


    }

}