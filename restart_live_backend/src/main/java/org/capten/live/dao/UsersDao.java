package org.capten.live.dao;

import lombok.AllArgsConstructor;
import org.capten.live.mapper.UsersMapper;
import org.capten.live.model.Users;
import org.capten.live.model.UsersExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.NoSuchElementException;

@Component
public class UsersDao {

    @Autowired
    private UsersMapper usersMapper;

    public Users getUserInfoByUserName(String username) {
        UsersExample usersExample = new UsersExample();
        usersExample.createCriteria().andUsernameEqualTo(username);
        List<Users> users = usersMapper.selectByExample(usersExample);
        try {
            return users.getFirst();
        } catch (NoSuchElementException e) {
            return null;
        }
    }

    public boolean registerUser(Users user) {
        int i = usersMapper.insertSelective(user);
        return i > 0;
    }

    public boolean updateUser(Users users) {
        return usersMapper.updateByPrimaryKey(users) > 0;
    }
}
