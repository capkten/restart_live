package org.capten.live.service;

import org.capten.live.domain.dto.ServiceResDto;
import org.capten.live.model.ReviewRecord;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 复盘记录 服务类
 * </p>
 *
 * @author capkin
 * @since 2025-02-02
 */
public interface IReviewRecordService extends IService<ReviewRecord> {

    ServiceResDto getAllWithRecordType(String token);

    ServiceResDto add(ReviewRecord reviewRecord, String token);

    boolean deleteByIdWithUser(Integer id, String token);
}
