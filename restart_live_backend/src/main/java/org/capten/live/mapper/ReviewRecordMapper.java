package org.capten.live.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.capten.live.model.ReviewRecord;
import com.github.yulichang.base.MPJBaseMapper;

import java.util.List;

/**
 * <p>
 * 复盘记录 Mapper 接口
 * </p>
 *
 * @author capkin
 * @since 2025-02-03
 */
public interface ReviewRecordMapper extends MPJBaseMapper<ReviewRecord> {

    List<ReviewRecord> getAllByReviewTypeIdsLimited(@Param("reviewTypeIds") List<Integer> reviewTypeIds);


}
