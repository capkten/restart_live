package org.capten.live.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.Version;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author capkin
 * @since 2025-02-03
 */
@TableName("money_pay_record")
public class MoneyPayRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String  id;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    @TableLogic
    private Boolean isDelete;

    @Version
    private Integer version;

    private String tagIds;

    private Integer way;

    private String  userId;

    private String name;

    private String remark;

    public String  getId() {
        return id;
    }

    public void setId(String  id) {
        this.id = id;
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

    public Boolean getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Boolean isDelete) {
        this.isDelete = isDelete;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getTagIds() {
        return tagIds;
    }

    public void setTagIds(String tagIds) {
        this.tagIds = tagIds;
    }

    public Integer getWay() {
        return way;
    }

    public void setWay(Integer way) {
        this.way = way;
    }

    public String  getUserId() {
        return userId;
    }

    public void setUserId(String  userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "MoneyPayRecord{" +
        "id = " + id +
        ", createTime = " + createTime +
        ", updateTime = " + updateTime +
        ", isDelete = " + isDelete +
        ", version = " + version +
        ", tagIds = " + tagIds +
        ", way = " + way +
        ", userId = " + userId +
        ", name = " + name +
        ", remark = " + remark +
        "}";
    }
}
