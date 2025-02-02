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
@TableName("runtime_task_engine")
public class RuntimeTaskEngine implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String  id;

    private String  uid;

    @TableLogic
    private LocalDateTime isDelete;

    private String  rttId;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    @Version
    private Integer version;

    private Integer belongingTimeWay;

    private String belongingTimeStr;

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

    public LocalDateTime getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(LocalDateTime isDelete) {
        this.isDelete = isDelete;
    }

    public String  getRttId() {
        return rttId;
    }

    public void setRttId(String  rttId) {
        this.rttId = rttId;
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

    public Integer getBelongingTimeWay() {
        return belongingTimeWay;
    }

    public void setBelongingTimeWay(Integer belongingTimeWay) {
        this.belongingTimeWay = belongingTimeWay;
    }

    public String getBelongingTimeStr() {
        return belongingTimeStr;
    }

    public void setBelongingTimeStr(String belongingTimeStr) {
        this.belongingTimeStr = belongingTimeStr;
    }

    @Override
    public String toString() {
        return "RuntimeTaskEngine{" +
        "id = " + id +
        ", uid = " + uid +
        ", isDelete = " + isDelete +
        ", rttId = " + rttId +
        ", createTime = " + createTime +
        ", updateTime = " + updateTime +
        ", version = " + version +
        ", belongingTimeWay = " + belongingTimeWay +
        ", belongingTimeStr = " + belongingTimeStr +
        "}";
    }
}
