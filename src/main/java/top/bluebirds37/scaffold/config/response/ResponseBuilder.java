package top.bluebirds37.scaffold.config.response;


import org.springframework.data.domain.Page;

import java.util.List;
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
     * @param
     * @param
     * @return
     */
    public static <T> ResponseBean<T> ok() {
        return ResponseBean.<T>builder().status(ResponseEnum.OK.status).massage(ResponseEnum.OK.message).build();
    }

    /**
     * 相应单一对象
     *
     * @param
     * @param
     * @return
     */
    public static <T> ResponseBean<T> ok(Integer status, String massage) {
        return ResponseBean.<T>builder().status(status).massage(massage).build();
    }

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
     * 相应单一对象
     *
     * @param data
     * @param <T>
     * @return
     */
    public static <T> ResponseBean<T> ok(T data, String message) {
        return ResponseBean.<T>builder().status(ResponseEnum.OK.status).massage(message).data(data).build();
    }


    /**
     * 构建分页
     *
     * @param function
     * @param <T>
     * @param <R>
     * @return
     */
    public static <T, R> ResponseBean<List<R>> ok(List<T> dataList, Function<? super T, ? extends R> function) {
        return ResponseBean.<List<R>>builder().data(buildBean(dataList, function)).status(ResponseEnum.OK.status).massage(ResponseEnum.OK.message).build();
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
     * @param function
     * @param <T>
     * @param <R>
     * @return
     */
    public static <T, R> List<R> buildBean(List<T> dataList, Function<? super T, ? extends R> function) {
        return dataList.stream().map(function).collect(Collectors.toList());
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
    public static <T> ResponseBean<T> fail() {
        return ResponseBean.<T>builder().status(ResponseEnum.FAIL.status).massage(ResponseEnum.FAIL.message).build();
    }

    /**
     * 失败
     */
    public static <T> ResponseBean<T> fail(Integer status, String message) {
        return ResponseBean.<T>builder().status(status).massage(message).build();
    }

    /**
     * 失败
     */
    public static <T> ResponseBean<T> fail(String message) {
        return ResponseBean.<T>builder().status(ResponseEnum.FAIL.status).massage(message).build();
    }

    /**
     * 失败
     *
     * @return
     */
    public static <T> ResponseBean<T> fail(T data) {
        return ResponseBean.<T>builder().status(ResponseEnum.FAIL.status).massage(ResponseEnum.FAIL.message).data(data).build();
    }

    public static <T> ResponseBean<T> fail(ResponseEnum responseEnum) {
        return ResponseBean.<T>builder().status(responseEnum.status).massage(responseEnum.message).build();
    }

}
