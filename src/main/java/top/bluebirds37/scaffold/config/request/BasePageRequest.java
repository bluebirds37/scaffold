package top.bluebirds37.scaffold.config.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @version 1.0
 * @Author mwh
 * @Date 2020/2/14 15:20
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BasePageRequest {

    private Integer page;

    private Integer size;

    private List<QueryRequest> queryRequestList;

    private List<SortRequest> sortRequestList;
}
