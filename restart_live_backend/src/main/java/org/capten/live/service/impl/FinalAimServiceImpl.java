package org.capten.live.service.impl;

import org.capten.live.dao.FinalAimDao;
import org.capten.live.dao.UsersDao;
import org.capten.live.domain.dto.ServiceResDto;
import org.capten.live.model.FinalAim;
import org.capten.live.mapper.FinalAimMapper;
import org.capten.live.model.Users;
import org.capten.live.service.IFinalAimService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.capten.live.service.bo.UsersBo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
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
public class FinalAimServiceImpl extends ServiceImpl<FinalAimMapper, FinalAim> implements IFinalAimService {

    @Autowired
    private FinalAimDao finalAimDao;

    @Autowired
    private UsersDao UsersDao;

    @Autowired
    private UsersBo usersBo;
    @Autowired
    private UsersDao usersDao;

    @Override
    public ServiceResDto getAll(String token) {
        String username = usersBo.getUserNameByToken(token);
        if (username == null) {
            return new ServiceResDto(UsersBo.USER_NOT_FOUND, null);
        }
        List<FinalAim> finalAims = finalAimDao.getAimsByUsername(username);
        return new ServiceResDto(UsersBo.USER_FINAL_AIM_GET_SUCCESS, finalAims);
    }

    @Override
    @Transactional
    public ServiceResDto change(List<FinalAim> finalAims, String token) {
        String userNameByToken = usersBo.getUserNameByToken(token);
        Users user = usersDao.getUserInfoByUserName(userNameByToken);
        List<FinalAim> currentAims = finalAimDao.getAimsByUsername(userNameByToken);
        List<Integer> deleteIds = new ArrayList<>();
        List<FinalAim> addAims = new ArrayList<>();
        List<FinalAim> updateAims = new ArrayList<>();
        for (FinalAim aim : finalAims) {
            if (aim.getId() == null || aim.getId() == 0) {
                aim.setId(null);
                aim.setUserId(user.getId());
                aim.setCreateTime(LocalDateTime.now());
                aim.setUpdateTime(LocalDateTime.now());
                aim.setIsFinished(false);
                addAims.add(aim);
            } else {
                for (FinalAim currentAim : currentAims) {
                    if (currentAim.getId().equals(aim.getId())) {
                        aim.setUpdateTime(LocalDateTime.now());
                        aim.setVersion(null);
                        updateAims.add(aim);
                        break;
                    }
                }
            }
        }
        for (FinalAim currentAim : currentAims) {
            boolean isIn = false;
            for (FinalAim aim : finalAims) {
                if (currentAim.getId().equals(aim.getId())) {
                    isIn = true;
                    break;
                }
            }
            if (!isIn) {
                currentAim.setUpdateTime(LocalDateTime.now());
                deleteIds.add(currentAim.getId());
            }
        }
        if (!deleteIds.isEmpty()) {
            int delete = finalAimDao.deleteByIds(deleteIds);
            if (delete == 0) {
                throw new RuntimeException("delete final aim fail");
            }
        }
        if (!addAims.isEmpty()) {
            int insert = finalAimDao.insertAims(addAims);
            if (insert == 0) {
                throw new RuntimeException("insert final aim fail");
            }
        }
        if (!updateAims.isEmpty()) {
            int update = finalAimDao.updateAims(updateAims);
            if (update == 0) {
                throw new RuntimeException("update final aim fail");
            }
        }
        return new ServiceResDto(UsersBo.USER_FINAL_AIM_GET_SUCCESS, getAll(token).data());
    }
}