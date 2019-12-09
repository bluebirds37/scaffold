package top.bluebirds37.scaffold.service.impl;

import org.springframework.http.ResponseEntity;
import top.bluebirds37.scaffold.config.authority.AuthenticationProperties;
import top.bluebirds37.scaffold.config.authority.JWTUtil;
import top.bluebirds37.scaffold.config.response.ResponseBean;
import top.bluebirds37.scaffold.config.response.ResponseBuilder;
import top.bluebirds37.scaffold.pojo.dto.system.UserDto;
import top.bluebirds37.scaffold.pojo.po.system.User;
import top.bluebirds37.scaffold.pojo.vo.system.UserRegisterVo;
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
    private AuthenticationProperties authenticationProperties;

    @Resource
    private UserRepository userRepository;

    @Override
    public ResponseEntity register(UserRegisterVo userRegisterVo) {
        User user = EntityUtils.copyProperties(userRegisterVo, User.class);
        userRepository.save(user);
        String sign = JWTUtil.sign(user.getId());
        return ResponseEntity.ok().header(authenticationProperties.getHeaderName(), sign).build();
    }

    @Override
    public ResponseBean<UserDto> findByUsername(String username) {
        User user = userRepository.findByUsername(username);
        UserDto userDto = EntityUtils.copyProperties(user, UserDto.class);
        return ResponseBuilder.ok(userDto);
    }

}
