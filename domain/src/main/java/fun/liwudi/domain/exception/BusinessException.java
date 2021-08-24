package fun.liwudi.domain.exception;

import fun.liwudi.domain.constants.Constant;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

/**
 * @author 李武第
 */
@Data
@ToString
public class BusinessException extends RuntimeException {

    public BusinessException(String code,String msg){
        this.code = code;
        this.msg = msg;
    }

    public BusinessException(String msg){
        this(Constant.ERROR_CODE,msg);
    }

    public BusinessException(){
        this(Constant.ERROR_CODE,Constant.FAIL);
    }

    private String msg;

    private String code;

}
