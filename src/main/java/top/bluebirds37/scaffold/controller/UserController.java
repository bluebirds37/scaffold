package top.bluebirds37.scaffold.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import top.bluebirds37.scaffold.config.response.ResponseBean;
import top.bluebirds37.scaffold.pojo.vo.UserRegisterVo;
import top.bluebirds37.scaffold.service.UserService;

import javax.annotation.Resource;

@RequestMapping("/user")
@Api(tags = "001.用户")
public class UserController {

    @Resource
    private UserService userService;

    @ApiOperation("注册")
    public ResponseEntity register(UserRegisterVo userRegisterVo) {
        return userService.register(userRegisterVo);
    }

}
