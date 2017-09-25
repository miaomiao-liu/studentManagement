package cn.edu.swpu.cins.studentManagement.entity.view;

import javax.xml.transform.Result;

/**
 * Created by miaomiao on 17-7-8.
 */
public class ResultData<T>{

    private boolean ifSuccess;
    private T data;
    private String msg;


    public ResultData(T data){
        this.ifSuccess=true;
        this.data=data;
    }

    public ResultData(boolean ifSuccess,T data){
        this.ifSuccess=ifSuccess;
        this.data=data;
    }


    public ResultData(boolean ifSuccess,String msg){
        this.ifSuccess=ifSuccess;
        this.msg=msg;
    }

    public ResultData(boolean ifSuccess,
                      T data,
                      String msg){
        this.ifSuccess=ifSuccess;
        this.data=data;
        this.msg=msg;
    }

    public ResultData(){}

    public boolean isIfSuccess() {
        return ifSuccess;
    }

    public void setIfSuccess(boolean ifSuccess) {
        this.ifSuccess = ifSuccess;
    }


    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }


    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
