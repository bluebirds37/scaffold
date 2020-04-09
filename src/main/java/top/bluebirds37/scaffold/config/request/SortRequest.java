package top.bluebirds37.scaffold.config.request;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @version 1.0
 * @Author mwh
 * @Date 2020/2/14 15:20
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SortRequest {

   private String fieldName;

   private SortEnum sortType;

}
