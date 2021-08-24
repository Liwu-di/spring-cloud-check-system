package fun.liwudi.authservice.bean;

import fun.liwudi.domain.bean.BaseDomain;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

/**
 * @author 李武第
 */
@Data
@ToString
@Builder
public class PublicVerifyDto extends BaseDomain {

    private String name;

    private String pass;
}
