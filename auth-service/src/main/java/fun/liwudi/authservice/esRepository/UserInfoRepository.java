package fun.liwudi.authservice.esRepository;

import fun.liwudi.authservice.bean.esbean.EsUserInfo;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

/**
 * @author 李武第
 */
@Repository
public interface UserInfoRepository extends ElasticsearchRepository<EsUserInfo,String> {
}
