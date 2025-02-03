package org.capten.live.dao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.capten.live.mapper.UsersMapper;
import org.capten.live.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;

@Component
public class UsersDao {

    @Autowired
    private UsersMapper usersMapper;

    public Users getUserInfoByUserName(String username) {
        Users users = usersMapper.selectOne(new QueryWrapper<Users>().eq("username", username));
        return users;
    }

    public boolean registerUser(Users user) {
        int i = usersMapper.insert(user);
        return i > 0;
    }

    public boolean updateUser(Users users) {
        return usersMapper.updateById(users) > 0;
    }
}
