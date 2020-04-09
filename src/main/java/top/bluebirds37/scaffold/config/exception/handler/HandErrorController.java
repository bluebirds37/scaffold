package top.bluebirds37.scaffold.config.exception.handler;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.bluebirds37.scaffold.config.response.ResponseBean;
import top.bluebirds37.scaffold.config.response.ResponseBuilder;
import top.bluebirds37.scaffold.config.response.ResponseEnum;

/**
 * @version 1.0
 * @Author mwh
 * @Date 2020/1/21 9:22
 */
@RestController
public class HandErrorController implements ErrorController {

    @Override
    public String getErrorPath() {
        return "/error";
    }

    @RequestMapping(value = "/error")
    public ResponseBean<?> error() {
        return ResponseBuilder.fail(ResponseEnum.NOTFOUND);
    }
}
