package fun.liwudi.authservice.service;

import fun.liwudi.authservice.bean.PublicVerifyDto;

/**
 * @author 李武第
 */
public interface PublicVerifyService {

    /**
     * check public ak
     * @param publicVerifyDto
     * @return
     */
    Boolean isExistAk(PublicVerifyDto publicVerifyDto);
}
