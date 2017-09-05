package springbootio.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import springbootio.service.MailService;

import java.util.Random;

/**
 * Created by miaomiao on 17-9-4.
 */
@Service
public class MailServiceImpl implements MailService {

    @Autowired
    private JavaMailSender sender;
    @Value("${spring.mail.username}")
    private String from;

    @Override
    public String sendRegisterMail(String username, String to) throws Exception{
        try {
            String verifyCode = getRandomString();
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setFrom(from);
            mailMessage.setTo(to);
            mailMessage.setSubject("尊敬的"+username+"欢迎注册学生管理平台");
            mailMessage.setText("您正在注册学生管理平台，验证码："+verifyCode+",请在学生管理平台注册页面输入验证码以激活您的邮箱");
            sender.send(mailMessage);
            return verifyCode;
        }catch (Exception e){
         throw new Exception("邮件发送失败！");
        }
    }

    @Override
    public String sendFindPwdMail(String to) throws Exception{
        try {
            String verifyCode = getRandomString();
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setFrom(from);
            mailMessage.setTo(to);
            mailMessage.setSubject("学生管理平台账户修改密码");
            mailMessage.setText("你的学生管理平台账户正在申请修改密码，验证码：" + verifyCode);
            sender.send(mailMessage);
            return verifyCode;
        }catch (Exception e){
            throw new Exception("邮件发送失败！");
        }
    }


    public static String getRandomString(){
        final char[] chars = "1234567890qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM".toCharArray();
        Random random = new Random();
        int index;
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < 4; i++){
            index = random.nextInt(chars.length);
            buffer.append(chars[index]);
        }
        return buffer.toString();
    }

}
