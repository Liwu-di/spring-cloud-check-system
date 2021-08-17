package fun.liwudi.domain.dto;

import fun.liwudi.domain.constants.Constant;

import java.util.ConcurrentModificationException;

/**
 * @author 李武第
 */
public class JsonResponse<T> {
    int code;
    String msg;
    T data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "JsonResponse{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }

    public Boolean judgeSuccess(){
        if(this.code == Constant.APPLICATION_SUCCESS){
            return true;
        }
        else {
            return false;
        }
    }
}
