package springbootio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * Created by muyi on 17-4-21.
 */
@Service
public class MailService {

    @Autowired
    private JavaMailSender sender;

    @Value("${spring.mail.username}")
    private String from;

    /**
     * 发送纯文本的简单邮件
     *
     * @param to
     * @param subject
     * @param content
     */

//    private String to = "yangquan95@163.com";


    public static final char[] chars = "1234567890QWERTYUIOPASDFGHJKLZXCVBNM".toCharArray();

    public static Random random = new Random();

    //    获取4位随机数
    public static String getRandomString() {
        StringBuffer buffer = new StringBuffer();
        int index;   //获取随机chars下标
        for (int i = 0; i < 4; i++) {
            index = random.nextInt(chars.length);  //获取随机chars下标
            buffer.append(chars[index]);
        }
        return buffer.toString();
    }

    public String sendSimpleMail(String to, String subject) {
        SimpleMailMessage message = new SimpleMailMessage();

        String verifyCode=getRandomString();
        message.setFrom(from);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(verifyCode);

        try {
            sender.send(message);
//            logger.info("简单邮件已经发送。");
        } catch (Exception e) {
//            logger.error("发送简单邮件时发生异常！", e);
        }
     return verifyCode;

    }
}
