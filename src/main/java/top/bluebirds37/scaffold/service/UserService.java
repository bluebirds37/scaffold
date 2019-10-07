package top.bluebirds37.scaffold.service;

import top.bluebirds37.scaffold.config.response.ResponseBean;
import top.bluebirds37.scaffold.pojo.dto.UserDto;

public interface UserService {

    ResponseBean<UserDto> findByUsername(String username);

}
