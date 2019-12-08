package top.bluebirds37.scaffold.config.exception.handler;

import top.bluebirds37.scaffold.config.exception.AuthenticationException;
import top.bluebirds37.scaffold.config.exception.AuthorityException;
import top.bluebirds37.scaffold.config.exception.CustomException;
import top.bluebirds37.scaffold.config.response.ResponseBean;
import top.bluebirds37.scaffold.config.response.ResponseBuilder;

import top.bluebirds37.scaffold.config.response.ResponseEnum;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;


@RestControllerAdvice
public class ExceptionController {

    /**
     * 异常
     */
    /*@ResponseStatus(HttpStatus.OK)
    @ExceptionHandler({
            Exception.class
    })
    public ResponseBean exceptionHandler(Exception e) {
        e.printStackTrace();
        return ResponseBuilder.fail(e.getMessage());
    }*/

    /**
     * 自定义异常
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler({
            CustomException.class
    })
    public ResponseBean handleCustomException(CustomException e) {
        e.printStackTrace();
        return ResponseBuilder.fail(e.getMessage());
    }


    /**
     * 权限不足
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler({
            AuthorityException.class
    })
    public ResponseBean handleAuthorityException(AuthorityException e) {
        e.printStackTrace();
        return ResponseBuilder.fail(ResponseEnum.FORBIDDEN.status, ResponseEnum.FORBIDDEN.message);
    }

    /**
     * 认证失败
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler({
            AuthenticationException.class
    })
    public ResponseBean handleAuthenticationException(AuthenticationException e) {
        e.printStackTrace();
        return ResponseBuilder.fail(ResponseEnum.UNAUTHORIZED.status, ResponseEnum.UNAUTHORIZED.message);
    }


    /**
     * 参数校验
     *
     * @param e
     * @return
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseBean handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        e.printStackTrace();
        List<String> errorList = new ArrayList<>();
        e.getBindingResult().getAllErrors().forEach(
                objectError -> {
                    errorList.add(objectError.getDefaultMessage());
                }
        );
        return ResponseBuilder.fail(errorList.toString());
    }

    /**
     * 参数校验
     *
     * @param e
     * @return
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler({BindException.class})
    public ResponseBean handleBindException(BindException e) {
        e.printStackTrace();
        List<String> errorList = new ArrayList<>();
        e.getBindingResult().getAllErrors().forEach(
                objectError -> {
                    errorList.add(objectError.getDefaultMessage());
                }
        );
        return ResponseBuilder.fail(errorList.toString());
    }


}
