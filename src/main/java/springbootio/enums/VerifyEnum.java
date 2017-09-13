package springbootio.enums;

import org.omg.CORBA.PRIVATE_MEMBER;

/**
 * Created by miaomiao on 17-9-6.
 */
public enum VerifyEnum {

    CHECK_REGISTER_MAIL("请到邮箱查看注册验证码！"),
    CHECK_PWD_MAIL("请到邮箱查看更改密码的验证码！"),
    EMPTY_USERNAME("用户名不能为空"),
    EMPTY_EMAIL("邮箱不能为空"),
    REPEAT_USERNAME("该用户名已被注册"),
    REPEAT_EMAIL("该邮箱已被注册"),
    NO_USER("用户名不存在"),
    MATE_USERNAME_MAIL("用户名与邮箱不匹配");

    private String msg;

    VerifyEnum(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
