package springbootio.service;

/**
 * Created by miaomiao on 17-9-4.
 */
public interface MailService {

    String sendRegisterMail(String username,String to) throws Exception;

    String sendFindPwdMail(String to) throws Exception;
}
