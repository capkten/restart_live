package org.capten.live.service.impl;

import org.capten.live.dao.ReviewRecordDao;
import org.capten.live.dao.ReviewTypeDao;
import org.capten.live.dao.UsersDao;
import org.capten.live.domain.dto.ServiceResDto;
import org.capten.live.domain.vo.ReviewRecordsVo;
import org.capten.live.model.ReviewRecord;
import org.capten.live.mapper.ReviewRecordMapper;
import org.capten.live.model.ReviewType;
import org.capten.live.model.Users;
import org.capten.live.service.IReviewRecordService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.capten.live.service.bo.UsersBo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 复盘记录 服务实现类
 * </p>
 *
 * @author capkin
 * @since 2025-02-02
 */
@Service
public class ReviewRecordServiceImpl extends ServiceImpl<ReviewRecordMapper, ReviewRecord> implements IReviewRecordService {

    @Autowired
    private UsersBo usersBo;

    @Autowired
    private ReviewRecordDao reviewRecordDao;

    @Autowired
    private ReviewTypeDao reviewTypeDao;

    @Autowired
    private UsersDao usersDao;

    @Override
    public ServiceResDto getAllWithRecordType(String token) {
        String userNameByToken = usersBo.getUserNameByToken(token);
        if (userNameByToken == null) {
            return new ServiceResDto(UsersBo.USER_NOT_FOUND, null);
        }
        List<ReviewType> reviewTypes = reviewTypeDao.getAllByUsername(userNameByToken);
        List<Integer> reviewTypeIds = new ArrayList<>();
        List<ReviewRecordsVo> reviewRecordsVos = new ArrayList<>();
        for (ReviewType reviewType : reviewTypes) {
            ReviewRecordsVo reviewRecordsVo = new ReviewRecordsVo();
            BeanUtils.copyProperties(reviewType, reviewRecordsVo);
            reviewRecordsVos.add(reviewRecordsVo);
            reviewTypeIds.add(reviewType.getId());
        }
        List<ReviewRecord> reviewRecords = reviewRecordDao.getAllByReviewTypeIds(reviewTypeIds);
        reviewRecordsVos.forEach(reviewRecordsVo -> {
            reviewRecordsVo.setReviewRecordList(new ArrayList<>());
            reviewRecords.forEach(reviewRecord -> {
                if (reviewRecordsVo.getId().equals(reviewRecord.getRtId())) {
                    reviewRecordsVo.getReviewRecordList().add(reviewRecord);
                }
            });
        });
        return new ServiceResDto(UsersBo.USER_REVIEW_GET_SUCCESS ,reviewRecordsVos);
    }

    @Override
    public ServiceResDto add(ReviewRecord reviewRecord, String token) {
        String userNameByToken = usersBo.getUserNameByToken(token);
        Users userInfoByUserName = usersDao.getUserInfoByUserName(userNameByToken);
        if (userInfoByUserName == null) {
            return new ServiceResDto(UsersBo.USER_NOT_FOUND, null);
        }
        reviewRecord.setUserId(userInfoByUserName.getId());
        if (reviewRecord.getDate() == null) {
            reviewRecord.setDate(LocalDateTime.now());
        }
        reviewRecord.setCreateTime(LocalDateTime.now());
        reviewRecord.setUpdateTime(LocalDateTime.now());
        if (reviewRecordDao.save(reviewRecord) > 0) {
            return new ServiceResDto(UsersBo.USER_REVIEW_ADD_SUCCESS, reviewRecord);
        }
        return new ServiceResDto(UsersBo.USER_REVIEW_ADD_ERR, null);
    }

    @Override
    public boolean deleteByIdWithUser(Integer id, String token) {
        String userNameByToken = usersBo.getUserNameByToken(token);
        return reviewRecordDao.deleteByIdWithUsername(id, userNameByToken) > 0;
    }
}
