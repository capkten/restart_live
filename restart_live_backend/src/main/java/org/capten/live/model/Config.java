package org.capten.live.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author capkin
 * @since 2025-02-03
 */
public class Config implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer uid;

    private String topBgImg;

    private String topBgIcon;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getTopBgImg() {
        return topBgImg;
    }

    public void setTopBgImg(String topBgImg) {
        this.topBgImg = topBgImg;
    }

    public String getTopBgIcon() {
        return topBgIcon;
    }

    public void setTopBgIcon(String topBgIcon) {
        this.topBgIcon = topBgIcon;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Config{" +
        "uid = " + uid +
        ", topBgImg = " + topBgImg +
        ", topBgIcon = " + topBgIcon +
        ", id = " + id +
        "}";
    }
}
