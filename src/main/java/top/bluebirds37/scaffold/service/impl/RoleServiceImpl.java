package top.bluebirds37.scaffold.service.impl;

import top.bluebirds37.scaffold.service.RoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = {Exception.class})
public class RoleServiceImpl implements RoleService {
}
