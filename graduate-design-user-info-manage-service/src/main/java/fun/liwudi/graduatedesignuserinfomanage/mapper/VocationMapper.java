package fun.liwudi.graduatedesignuserinfomanage.mapper;

import fun.liwudi.graduatedesignuserinfomanage.domain.Vocation;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.web.bind.annotation.ModelAttribute;

/**
 * @author 李武第
 */
@Mapper
public interface VocationMapper {

    /**
     * 保存记录
     * @param vocation
     * @return
     */
    int save(Vocation vocation);
}
