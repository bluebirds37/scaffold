package top.bluebirds37.scaffold.controller;


import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import top.bluebirds37.scaffold.service.UserService;

import javax.annotation.Resource;

@RequestMapping("/user")
@Api(tags = "001.用户")
public class UserController {

    @Resource
    private UserService userService;


}
