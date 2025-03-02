package org.capten.live.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
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
public class Modules implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer pid;

    private Integer lastId;

    private String name;

    private String introduce;

    private Boolean isFinish;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    @TableLogic
    private Boolean isDelete;

    @Version
    private Integer version;

    private BigDecimal stars;

    private LocalDateTime belongingTime;

    private LocalDateTime startTime;

    private Integer moduelesType;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Integer getLastId() {
        return lastId;
    }

    public void setLastId(Integer lastId) {
        this.lastId = lastId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public Boolean getIsFinish() {
        return isFinish;
    }

    public void setIsFinish(Boolean isFinish) {
        this.isFinish = isFinish;
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

    public BigDecimal getStars() {
        return stars;
    }

    public void setStars(BigDecimal stars) {
        this.stars = stars;
    }

    public LocalDateTime getBelongingTime() {
        return belongingTime;
    }

    public void setBelongingTime(LocalDateTime belongingTime) {
        this.belongingTime = belongingTime;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public Integer getModuelesType() {
        return moduelesType;
    }

    public void setModuelesType(Integer moduelesType) {
        this.moduelesType = moduelesType;
    }

    @Override
    public String toString() {
        return "Modules{" +
        "id = " + id +
        ", pid = " + pid +
        ", lastId = " + lastId +
        ", name = " + name +
        ", introduce = " + introduce +
        ", isFinish = " + isFinish +
        ", createTime = " + createTime +
        ", updateTime = " + updateTime +
        ", isDelete = " + isDelete +
        ", version = " + version +
        ", stars = " + stars +
        ", belongingTime = " + belongingTime +
        ", startTime = " + startTime +
        ", moduelesType = " + moduelesType +
        "}";
    }
}
