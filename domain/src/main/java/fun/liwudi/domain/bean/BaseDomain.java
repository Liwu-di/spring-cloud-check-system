package fun.liwudi.domain.bean;

import lombok.Data;
import lombok.ToString;

import java.math.BigInteger;

/**
 * @author 李武第
 */
@Data
@ToString
public class BaseDomain {

    private BigInteger id;

    private String createTime;

    private String createUser;

    private String updateUser;

    private String updateTime;

    private Integer deleteFlag;
}
