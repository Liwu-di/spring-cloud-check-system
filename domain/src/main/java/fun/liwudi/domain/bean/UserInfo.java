package fun.liwudi.domain.bean;

import lombok.*;

/**
 * @author 李武第
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserInfo extends BaseDomain {

    private String userCode;

    private String userName;

    private String password;
}
