package org.capten.live.dao;

import lombok.AllArgsConstructor;
import org.capten.live.mapper.UsersMapper;
import org.capten.live.model.Users;
import org.capten.live.model.UsersExample;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class UsersDao {

    private UsersMapper usersMapper;

    public Users getUserInfoByUserName(String username) {
        UsersExample usersExample = new UsersExample();
        usersExample.createCriteria().andUsernameEqualTo(username);
        List<Users> users = usersMapper.selectByExample(usersExample);
        return users.getFirst();
    }
}
