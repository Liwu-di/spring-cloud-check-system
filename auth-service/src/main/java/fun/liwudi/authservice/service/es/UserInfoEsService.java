package fun.liwudi.authservice.service.es;

import fun.liwudi.authservice.bean.esbean.EsUserInfo;
import fun.liwudi.domain.dto.PageResponse;

import java.util.List;
/**
 * @author 李武第
 */
public interface UserInfoEsService {

    long count();

    EsUserInfo save(EsUserInfo esUserInfo);

    void delete(EsUserInfo esUserInfo);

    List<EsUserInfo> getAll();

    List<EsUserInfo> getByName(String name);

    List<EsUserInfo> getByCode(String code);

    PageResponse<EsUserInfo> pageQuery(Integer pageNo, Integer pageSize, String kw);
}
