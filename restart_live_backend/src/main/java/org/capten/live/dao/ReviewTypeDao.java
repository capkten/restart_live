package org.capten.live.dao;

import com.github.yulichang.wrapper.MPJLambdaWrapper;
import org.apache.catalina.User;
import org.capten.live.mapper.ReviewTypeMapper;
import org.capten.live.model.ReviewType;
import org.capten.live.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class ReviewTypeDao {

    @Autowired
    private ReviewTypeMapper reviewTypeMapper;

    public List<ReviewType> getAllByUsername(String userNameByToken) {
        List<ReviewType> reviewTypes = reviewTypeMapper.selectJoinList(new MPJLambdaWrapper<ReviewType>()
                .leftJoin(Users.class, Users::getId, ReviewType::getUserId)
                .eq(Users::getUsername, userNameByToken)
                .selectAll(ReviewType.class));
        return reviewTypes;
    }

    public int save(ReviewType reviewType) {
        return reviewTypeMapper.insert(reviewType);
    }

    public int deleteByIdWithUsername(Integer id, String username) {
//        int i = reviewTypeMapper.deleteJoin(new MPJLambdaWrapper<ReviewType>()
//                .eq(ReviewType::getId, id)
//                .leftJoin(Users.class, Users::getId, ReviewType::getUserId)
//                .eq(Users::getUsername, username));
        Long count = reviewTypeMapper.selectJoinCount(new MPJLambdaWrapper<ReviewType>()
                .eq(ReviewType::getId, id)
                .leftJoin(Users.class, Users::getId, ReviewType::getUserId)
                .eq(Users::getUsername, username));
        if (count == 1) {
            return reviewTypeMapper.deleteById(id);
        }
        return 0;
    }

    public int updateNameByIdWithUsername(Integer id, String name, String username) {
        ReviewType reviewType = reviewTypeMapper.selectJoinOne(new MPJLambdaWrapper<ReviewType>()
                .eq(ReviewType::getId, id)
                .leftJoin(Users.class, Users::getId, ReviewType::getUserId)
                .eq(Users::getUsername, username)
                .selectAll(ReviewType.class));
        if (reviewType == null) {
            return 0;
        }
        reviewType = new ReviewType();
        reviewType.setName(name);
        reviewType.setId(id);
        reviewType.setUpdateTime(LocalDateTime.now());
        return reviewTypeMapper.updateById(reviewType);
    }
}
