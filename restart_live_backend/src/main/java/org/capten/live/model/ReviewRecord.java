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
 * 复盘记录
 * </p>
 *
 * @author capkin
 * @since 2025-02-03
 */
@TableName("review_record")
public class ReviewRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String  id;

    private String  userId;

    private String  rtId;

    @TableLogic
    private Boolean isDelete;

    private String content;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

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

    public String  getRtId() {
        return rtId;
    }

    public void setRtId(String  rtId) {
        this.rtId = rtId;
    }

    public Boolean getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Boolean isDelete) {
        this.isDelete = isDelete;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    @Override
    public String toString() {
        return "ReviewRecord{" +
        "id = " + id +
        ", userId = " + userId +
        ", rtId = " + rtId +
        ", isDelete = " + isDelete +
        ", content = " + content +
        ", createTime = " + createTime +
        ", updateTime = " + updateTime +
        ", version = " + version +
        "}";
    }
}
