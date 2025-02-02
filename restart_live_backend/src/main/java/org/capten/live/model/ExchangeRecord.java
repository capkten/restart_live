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
@TableName("exchange_record")
public class ExchangeRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String  id;

    private String  asId;

    private String  userId;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    @TableLogic
    private Boolean isDelete;

    private Integer stars;

    private String asName;

    private String asCoverUrl;

    private String asIntroduce;

    @Version
    private Integer version;

    public String  getId() {
        return id;
    }

    public void setId(String  id) {
        this.id = id;
    }

    public String  getAsId() {
        return asId;
    }

    public void setAsId(String  asId) {
        this.asId = asId;
    }

    public String  getUserId() {
        return userId;
    }

    public void setUserId(String  userId) {
        this.userId = userId;
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

    public Integer getStars() {
        return stars;
    }

    public void setStars(Integer stars) {
        this.stars = stars;
    }

    public String getAsName() {
        return asName;
    }

    public void setAsName(String asName) {
        this.asName = asName;
    }

    public String getAsCoverUrl() {
        return asCoverUrl;
    }

    public void setAsCoverUrl(String asCoverUrl) {
        this.asCoverUrl = asCoverUrl;
    }

    public String getAsIntroduce() {
        return asIntroduce;
    }

    public void setAsIntroduce(String asIntroduce) {
        this.asIntroduce = asIntroduce;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "ExchangeRecord{" +
        "id = " + id +
        ", asId = " + asId +
        ", userId = " + userId +
        ", createTime = " + createTime +
        ", updateTime = " + updateTime +
        ", isDelete = " + isDelete +
        ", stars = " + stars +
        ", asName = " + asName +
        ", asCoverUrl = " + asCoverUrl +
        ", asIntroduce = " + asIntroduce +
        ", version = " + version +
        "}";
    }
}
