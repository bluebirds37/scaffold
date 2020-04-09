package top.bluebirds37.scaffold.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import top.bluebirds37.scaffold.pojo.vo.req.system.UserRegisterReq;
import top.bluebirds37.scaffold.service.UserService;

import javax.annotation.Resource;

@RequestMapping("/user")
@Api(tags = "001.用户")
public class UserController {

    @Resource
    private UserService userService;

    @ApiOperation("注册")
    @PostMapping(value = "/register")
    public ResponseEntity<?> register(UserRegisterReq userRegisterReq) {
        return userService.register(userRegisterReq);
    }

}
