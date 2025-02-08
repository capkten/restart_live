package org.capten.live.dao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.yulichang.interfaces.MPJBaseJoin;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import org.capten.live.mapper.ReviewRecordMapper;
import org.capten.live.model.ReviewRecord;
import org.capten.live.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ReviewRecordDao {

    @Autowired
    private ReviewRecordMapper reviewRecordMapper;

    public List<ReviewRecord> getAllByReviewTypeIds(List<Integer> reviewTypeIds) {
        if (reviewTypeIds.isEmpty()) {
            return new ArrayList<>();
        }
//        List<ReviewRecord> reviewRecords = reviewRecordMapper.selectList(new QueryWrapper<ReviewRecord>().in("rt_id", reviewTypeIds));
        List<ReviewRecord> reviewRecords = reviewRecordMapper.getAllByReviewTypeIdsLimited(reviewTypeIds);
        return reviewRecords;
    }

    public int save(ReviewRecord reviewRecord) {
        return reviewRecordMapper.insert(reviewRecord);
    }

    public int deleteByIdWithUsername(Integer id, String userNameByToken) {
//        return reviewRecordMapper.deleteJoin(new MPJLambdaWrapper<ReviewRecord>()
//                .leftJoin(Users.class, Users::getId, ReviewRecord::getUserId)
//                .eq(ReviewRecord::getId, id)
//                .eq(Users::getUsername, userNameByToken));
        Long count = reviewRecordMapper.selectJoinCount(new MPJLambdaWrapper<ReviewRecord>()
                .leftJoin(Users.class, Users::getId, ReviewRecord::getUserId)
                .eq(ReviewRecord::getId, id)
                .eq(Users::getUsername, userNameByToken));
        if (count == 1) {
            return reviewRecordMapper.deleteById(id);
        }else {
            return 0;
        }
    }
}
