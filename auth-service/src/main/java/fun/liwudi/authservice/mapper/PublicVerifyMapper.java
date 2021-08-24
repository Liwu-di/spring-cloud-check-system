package fun.liwudi.authservice.mapper;

import fun.liwudi.authservice.bean.PublicVerifyDto;
import fun.liwudi.domain.bean.BaseDomain;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author 李武第
 */
@Mapper
public interface PublicVerifyMapper {

    /**
     * find one
     * @param publicVerifyDto
     * @return
     */
    PublicVerifyDto findOne(PublicVerifyDto publicVerifyDto);
}
