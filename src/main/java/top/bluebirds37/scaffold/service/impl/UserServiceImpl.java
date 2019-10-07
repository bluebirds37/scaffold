package top.bluebirds37.scaffold.service.impl;

import top.bluebirds37.scaffold.config.response.ResponseBean;
import top.bluebirds37.scaffold.config.response.ResponseBuilder;
import top.bluebirds37.scaffold.pojo.dto.UserDto;
import top.bluebirds37.scaffold.pojo.entity.User;
import top.bluebirds37.scaffold.repository.UserRepository;
import top.bluebirds37.scaffold.service.UserService;
import top.bluebirds37.scaffold.util.EntityUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import javax.annotation.Resource;

@Service
@Transactional(rollbackFor = {Exception.class})
public class UserServiceImpl implements UserService {

    @Resource
    private UserRepository userRepository;

    @Override
    public ResponseBean<UserDto> findByUsername(String username) {
        User user = userRepository.findByUsername(username);
        UserDto userDto = EntityUtils.copyProperties(user, UserDto.class);
        return ResponseBuilder.ok(userDto);
    }

}
