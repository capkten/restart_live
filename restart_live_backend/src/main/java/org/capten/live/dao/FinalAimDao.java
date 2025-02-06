package org.capten.live.dao;

import com.github.yulichang.query.MPJQueryWrapper;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import org.apache.ibatis.executor.BatchResult;
import org.capten.live.mapper.FinalAimMapper;
import org.capten.live.model.FinalAim;
import org.capten.live.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FinalAimDao {

    @Autowired
    private FinalAimMapper finalAimMapper;

    public List<FinalAim> getAimsByUsername(String username) {
        List<FinalAim> finalAims = finalAimMapper.selectJoinList(FinalAim.class,
                new MPJLambdaWrapper<FinalAim>()
                        .leftJoin(Users.class, Users::getId, FinalAim::getUserId)
                        .eq(Users::getUsername, username)
                        .selectAll(FinalAim.class));
        return finalAims;
    }

    public int deleteByIds(List<Integer> deleteIds) {
        return finalAimMapper.deleteByIds(deleteIds);
    }

    public int insertAims(List<FinalAim> addAims) {
        List<BatchResult> insert = finalAimMapper.insert(addAims);
        int affectedRows = 0;
        for (BatchResult result : insert) {
            for (int updateCount : result.getUpdateCounts()) {
                affectedRows += updateCount;
            }
        }
        return affectedRows;
    }

    public int updateAims(List<FinalAim> updateAims) {
        List<BatchResult> batchResults = finalAimMapper.updateById(updateAims);
        int affectedRows = 0;
        for (BatchResult result : batchResults) {
            for (int updateCount : result.getUpdateCounts()) {
                affectedRows += updateCount;
            }
        }
        return affectedRows;
    }
}
