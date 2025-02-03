package org.capten.live.service.impl;

import org.capten.live.model.Users;
import org.capten.live.mapper.UsersMapper;
import org.capten.live.service.IUsersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author capkin
 * @since 2025-02-03
 */
@Service
public class UsersServiceImpl extends ServiceImpl<UsersMapper, Users> implements IUsersService {

}
