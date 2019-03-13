package com.cc.greendao_demo.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * 实体类
 */
@Entity
public class UserInfo {

    /** id（主键，自增。千万要注意，不能用int） */
    @Id(autoincrement = true)
    private Long id;

    /** 姓名（不可为空） */
    private String name;

    /** 电话（可以为空） */
    private String phonenum;

    @Generated(hash = 1569753061)
    public UserInfo(Long id, String name, String phonenum) {
        this.id = id;
        this.name = name;
        this.phonenum = phonenum;
    }

    @Generated(hash = 1279772520)
    public UserInfo() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhonenum() {
        return this.phonenum;
    }

    public void setPhonenum(String phonenum) {
        this.phonenum = phonenum;
    }

}
