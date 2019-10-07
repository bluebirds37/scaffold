package top.bluebirds37.scaffold.config.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResponseBean<T> {

    private Integer status;

    private String massage;

    private T data;

}
