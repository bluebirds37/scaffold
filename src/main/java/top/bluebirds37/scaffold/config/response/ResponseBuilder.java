package top.bluebirds37.scaffold.config.response;


import org.springframework.data.domain.Page;

import java.util.function.Function;
import java.util.stream.Collectors;


/**
 * 构建统一响应格式
 *
 * @param <T>
 */

public class ResponseBuilder<T> {

    /**
     * 相应单一对象
     *
     * @param data
     * @param <T>
     * @return
     */
    public static <T> ResponseBean<T> ok(T data) {
        return ResponseBean.<T>builder().status(ResponseEnum.OK.status).massage(ResponseEnum.OK.message).data(data).build();
    }

    /**
     * 构建分页
     *
     * @param page
     * @param function
     * @param <T>
     * @param <R>
     * @return
     */
    public static <T, R> ResponseBean<PageBean<R>> ok(Page<T> page, Function<? super T, ? extends R> function) {

        return ResponseBean.<PageBean<R>>builder().data(buildPageBean(page, function)).status(ResponseEnum.OK.status).massage(ResponseEnum.OK.message).build();
    }

    /**
     * 构建分页
     *
     * @param page
     * @param function
     * @param <T>
     * @param <R>
     * @return
     */
    public static <T, R> PageBean<R> buildPageBean(Page<T> page, Function<? super T, ? extends R> function) {
        return PageBean
                .<R>builder()
                .row(
                        page.getContent().stream().map(function).collect(Collectors.toList())
                )
                .total(page.getTotalElements())
                .page(page.getTotalPages())
                .build();
    }

    /**
     * 失败
     *
     * @return
     */
    public static ResponseBean fail() {
        return ResponseBean.builder().status(ResponseEnum.FAIL.status).massage(ResponseEnum.FAIL.message).build();
    }

    /**
     * 失败
     */
    public static ResponseBean fail(Integer status, String message) {
        return ResponseBean.builder().status(status).massage(message).build();
    }

    /**
     * 失败
     */
    public static ResponseBean fail(String message) {
        return ResponseBean.builder().status(ResponseEnum.FAIL.status).massage(message).build();
    }

    /**
     * 失败
     *
     * @return
     */
    public static <T> ResponseBean<T> fail(T data) {
        return ResponseBean.<T>builder().status(ResponseEnum.FAIL.status).massage(ResponseEnum.FAIL.message).data(data).build();
    }

}
