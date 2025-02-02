package org.capten.live.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
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
@TableName("runtime_task_template")
public class RuntimeTaskTemplate implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String  id;

    private String  uid;

    private String name;

    @Version
    private Integer version;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private String remark;

    private BigDecimal stars;

    private String belongingTime;

    @TableLogic
    private Boolean isDelete;

    public String  getId() {
        return id;
    }

    public void setId(String  id) {
        this.id = id;
    }

    public String  getUid() {
        return uid;
    }

    public void setUid(String  uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public BigDecimal getStars() {
        return stars;
    }

    public void setStars(BigDecimal stars) {
        this.stars = stars;
    }

    public String getBelongingTime() {
        return belongingTime;
    }

    public void setBelongingTime(String belongingTime) {
        this.belongingTime = belongingTime;
    }

    public Boolean getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Boolean isDelete) {
        this.isDelete = isDelete;
    }

    @Override
    public String toString() {
        return "RuntimeTaskTemplate{" +
        "id = " + id +
        ", uid = " + uid +
        ", name = " + name +
        ", version = " + version +
        ", createTime = " + createTime +
        ", updateTime = " + updateTime +
        ", remark = " + remark +
        ", stars = " + stars +
        ", belongingTime = " + belongingTime +
        ", isDelete = " + isDelete +
        "}";
    }
}
