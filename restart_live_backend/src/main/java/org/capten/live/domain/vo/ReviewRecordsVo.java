package org.capten.live.domain.vo;

import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.Version;
import org.capten.live.model.ReviewRecord;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;


public class ReviewRecordsVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private Integer userId;

    private String name;

    private Integer sort;

    private Boolean isDelete;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private Integer version;

    private List<ReviewRecord> reviewRecordList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Boolean getDelete() {
        return isDelete;
    }

    public void setDelete(Boolean delete) {
        isDelete = delete;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public List<ReviewRecord> getReviewRecordList() {
        return reviewRecordList;
    }

    public void setReviewRecordList(List<ReviewRecord> reviewRecordList) {
        this.reviewRecordList = reviewRecordList;
    }

    @Override
    public String toString() {
        return "ReviewRecordsVo{" +
                "id=" + id +
                ", userId=" + userId +
                ", name='" + name + '\'' +
                ", sort=" + sort +
                ", isDelete=" + isDelete +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", version=" + version +
                ", reviewRecordList=" + reviewRecordList +
                '}';
    }
}
