package top.bluebirds37.scaffold.config.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 构建统一分页相应
 *
 * @param <T>
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PageBean<T> {

    private Integer page;

    private Long total;

    private List<T> row;

}
