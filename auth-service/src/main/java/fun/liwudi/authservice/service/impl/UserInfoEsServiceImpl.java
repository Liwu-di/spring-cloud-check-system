package fun.liwudi.authservice.service.impl;

import fun.liwudi.authservice.bean.esbean.EsUserInfo;
import fun.liwudi.authservice.esRepository.UserInfoRepository;
import fun.liwudi.authservice.service.es.UserInfoEsService;
import fun.liwudi.domain.dto.PageResponse;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author 李武第
 */
@Service
public class UserInfoEsServiceImpl implements UserInfoEsService {

    @Resource
    private UserInfoRepository userInfoRepository;


    @Override
    public long count() {
        return userInfoRepository.count();
    }

    @Override
    public EsUserInfo save(EsUserInfo esUserInfo) {
        return userInfoRepository.save(esUserInfo);
    }

    @Override
    public void delete(EsUserInfo esUserInfo) {
        userInfoRepository.delete(esUserInfo);
    }

    @Override
    public List<EsUserInfo> getAll() {
        List<EsUserInfo> list = new ArrayList<>(16);
        Iterable<EsUserInfo> iterable = userInfoRepository.findAll();
        if(Objects.nonNull(iterable)){
            iterable.forEach(e -> {
                list.add(e);
            });
        }
        return list;
    }

    @Override
    public List<EsUserInfo> getByName(String name) {
        List<EsUserInfo> list = new ArrayList<>(16);
        Iterable<EsUserInfo> iterable = userInfoRepository.search(QueryBuilders.boolQuery().
                must(QueryBuilders.termQuery("user_name",name)));
        iterable.forEach(esUserInfo -> list.add(esUserInfo));
        return list;
    }

    @Override
    public List<EsUserInfo> getByCode(String code) {
        List<EsUserInfo> list = new ArrayList<>(16);
        Iterable<EsUserInfo> iterable = userInfoRepository.search(QueryBuilders.boolQuery().
                must(QueryBuilders.termQuery("user_code",code)));
        iterable.forEach(esUserInfo -> list.add(esUserInfo));
        return list;
    }

    @Override
    public PageResponse<EsUserInfo> pageQuery(Integer pageNo, Integer pageSize, String kw) {
        NativeSearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(QueryBuilders.matchPhraseQuery("user_name", kw))
                .withPageable(PageRequest.of(pageNo, pageSize))
                .build();
        Page page = userInfoRepository.search(searchQuery);
        PageResponse pageResponse = PageResponse.builder()
                .pageNo(page.getNumber())
                .pageSize(page.getSize())
                .totalPage(page.getTotalPages())
                .data(page.getContent())
                .build();
        return pageResponse;
    }
}
