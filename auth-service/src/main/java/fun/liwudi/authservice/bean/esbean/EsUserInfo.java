package fun.liwudi.authservice.bean.esbean;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.io.Serializable;
import java.math.BigInteger;

/**
 * @author 李武第
 */
@Data
@Document(indexName = "user_info",type = "_doc")
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EsUserInfo implements Serializable {

    //@Id
    //private String esId;

    private BigInteger id;

    private String createTime;

    private String createUser;

    private String updateUser;

    private String updateTime;

    private Integer deleteFlag;

    private String userCode;

    private String userName;

    private String password;
}
