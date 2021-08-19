package fun.liwudi.authservice.mapper;

import fun.liwudi.domain.bean.BaseDomain;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author 李武第
 */
@Mapper
public interface test {

    void test(BaseDomain baseDomain);
}
