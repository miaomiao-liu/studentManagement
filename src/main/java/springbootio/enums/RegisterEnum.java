package springbootio.enums;

/**
 * Created by miaomiao on 17-7-19.
 */
public enum RegisterEnum {

    REGISTER_SUCCESS("注册成功！"),
    REGISTER_FAIL("注册失败!"),
    EMPTY_PWD("密码不能为空！");

    private String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    RegisterEnum(String msg) {
        this.msg = msg;
    }
}
