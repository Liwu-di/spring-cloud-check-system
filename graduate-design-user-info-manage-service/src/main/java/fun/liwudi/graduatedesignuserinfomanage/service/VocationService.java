package fun.liwudi.graduatedesignuserinfomanage.service;

import fun.liwudi.graduatedesignuserinfomanage.domain.JsonResponse;
import fun.liwudi.graduatedesignuserinfomanage.domain.Vocation;

/**
 * @author 李武第
 */
public interface VocationService {

    /**
     * 发邮件
     * @param vocation
     * @return
     */
    JsonResponse sendMail(Vocation vocation);
}
