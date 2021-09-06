package fun.liwudi.domain.dto;

import lombok.*;
import java.util.List;
/**
 * @author 李武第
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PageResponse<T> {

    private List<T> data;

    private Integer pageSize = 30;

    private Integer pageNo = 1;

    private Integer start = 0;

    private Integer totalPage;
}
