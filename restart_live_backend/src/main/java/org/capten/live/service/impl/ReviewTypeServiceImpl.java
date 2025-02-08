package org.capten.live.service.impl;

import org.capten.live.dao.ReviewTypeDao;
import org.capten.live.dao.UsersDao;
import org.capten.live.domain.dto.ServiceResDto;
import org.capten.live.model.ReviewType;
import org.capten.live.mapper.ReviewTypeMapper;
import org.capten.live.model.Users;
import org.capten.live.service.IReviewTypeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.capten.live.service.bo.UsersBo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author capkin
 * @since 2025-02-02
 */
@Service
public class ReviewTypeServiceImpl extends ServiceImpl<ReviewTypeMapper, ReviewType> implements IReviewTypeService {

    @Autowired
    private ReviewTypeDao reviewTypeDao;

    @Autowired
    private UsersBo usersBo;

    @Autowired
    private UsersDao usersDao;

    @Override
    public ServiceResDto getAll(String token) {
        String userNameByToken = usersBo.getUserNameByToken(token);
        if (userNameByToken == null) {
            return new ServiceResDto(UsersBo.USER_NOT_FOUND, null);
        }
        List<ReviewType> reviewTypeList = reviewTypeDao.getAllByUsername(userNameByToken);
        return new ServiceResDto(UsersBo.USER_INFO_SUCCESS, reviewTypeList);
    }

    @Override
    public ServiceResDto add(String name, Integer sort, String token) {
        String username = usersBo.getUserNameByToken(token);
        if (username == null) {
            return new ServiceResDto(UsersBo.USER_NOT_FOUND, null);
        }
        Users userInfoByUserName = usersDao.getUserInfoByUserName(username);
        if (userInfoByUserName == null) {
            return new ServiceResDto(UsersBo.USER_NOT_FOUND, null);
        }
        ReviewType reviewType = new ReviewType();
        reviewType.setName(name);
        reviewType.setUserId(userInfoByUserName.getId());
        reviewType.setCreateTime(LocalDateTime.now());
        reviewType.setUpdateTime(LocalDateTime.now());
        reviewType.setSort(sort);
        if (reviewTypeDao.save(reviewType) > 0) {
            return new ServiceResDto(UsersBo.USER_REVIEW_TYPE_SUCCESS, reviewType);
        }
        return new ServiceResDto(UsersBo.USER_REVIEW_TYPE_ERR, null);
    }

    @Override
    public ServiceResDto delete(Integer id, String token) {
        String username = usersBo.getUserNameByToken(token);
        if (username == null) {
            return new ServiceResDto(UsersBo.USER_NOT_FOUND, null);
        }
        if (reviewTypeDao.deleteByIdWithUsername(id, username) > 0) {
            return new ServiceResDto(UsersBo.USER_REVIEW_TYPE_SUCCESS, null);
        }
        return new ServiceResDto(UsersBo.USER_REVIEW_TYPE_ERR, null);
    }

    @Override
    public ServiceResDto changeName(Integer id, String name, String token) {
        String username = usersBo.getUserNameByToken(token);
        int update = reviewTypeDao.updateNameByIdWithUsername(id, name, username);
        if (update > 0) {
            return new ServiceResDto(UsersBo.USER_REVIEW_TYPE_SUCCESS, null);
        }
        return new ServiceResDto(UsersBo.USER_REVIEW_TYPE_ERR, null);
    }
}
