package top.bluebirds37.scaffold.service;

import org.springframework.http.ResponseEntity;
import top.bluebirds37.scaffold.config.response.ResponseBean;
import top.bluebirds37.scaffold.pojo.vo.res.system.UserRes;
import top.bluebirds37.scaffold.pojo.vo.req.system.UserRegisterReq;

public interface UserService {

    ResponseBean<UserRes> findByUsername(String username);

    ResponseEntity register(UserRegisterReq userRegisterReq);

}
