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

    /**
     * 保存请销假记录
     * @param vocation
     */
    void saveRecord(Vocation vocation);
}
