package org.capten.live.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.Version;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author capkin
 * @since 2025-02-03
 */
public class Tasks implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String  id;

    private String  userId;

    private Short type;

    private String content;

    private Boolean isFinished;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private LocalDateTime finishTime;

    private String remark;

    private BigDecimal starts;

    private Integer experience;

    @Version
    private Integer version;

    public String  getId() {
        return id;
    }

    public void setId(String  id) {
        this.id = id;
    }

    public String  getUserId() {
        return userId;
    }

    public void setUserId(String  userId) {
        this.userId = userId;
    }

    public Short getType() {
        return type;
    }

    public void setType(Short type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Boolean getIsFinished() {
        return isFinished;
    }

    public void setIsFinished(Boolean isFinished) {
        this.isFinished = isFinished;
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

    public LocalDateTime getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(LocalDateTime finishTime) {
        this.finishTime = finishTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public BigDecimal getStarts() {
        return starts;
    }

    public void setStarts(BigDecimal starts) {
        this.starts = starts;
    }

    public Integer getExperience() {
        return experience;
    }

    public void setExperience(Integer experience) {
        this.experience = experience;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "Tasks{" +
        "id = " + id +
        ", userId = " + userId +
        ", type = " + type +
        ", content = " + content +
        ", isFinished = " + isFinished +
        ", createTime = " + createTime +
        ", updateTime = " + updateTime +
        ", finishTime = " + finishTime +
        ", remark = " + remark +
        ", starts = " + starts +
        ", experience = " + experience +
        ", version = " + version +
        "}";
    }
}
