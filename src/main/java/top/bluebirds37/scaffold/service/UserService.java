package top.bluebirds37.scaffold.service;

import org.springframework.http.ResponseEntity;
import top.bluebirds37.scaffold.config.response.ResponseBean;
import top.bluebirds37.scaffold.pojo.dto.UserDto;
import top.bluebirds37.scaffold.pojo.vo.UserRegisterVo;

public interface UserService {

    ResponseBean<UserDto> findByUsername(String username);

    ResponseEntity register(UserRegisterVo userRegisterVo);

}
