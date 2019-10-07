package top.bluebirds37.scaffold.service.impl;

import top.bluebirds37.scaffold.service.PermissionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = {Exception.class})
public class PermissionServiceImpl implements PermissionService {
}
