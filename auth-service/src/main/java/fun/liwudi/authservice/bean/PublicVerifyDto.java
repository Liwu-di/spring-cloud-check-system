package fun.liwudi.authservice.bean;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

/**
 * @author 李武第
 */
@Data
@ToString
@Builder
public class PublicVerifyDto {

    private String name;

    private String pass;
}
