package com.wujia.entity;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author 吾嘉
 * @since 2019-06-20
 */
public class Users implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "u_id", type = IdType.AUTO)
    private Long uId;
    private String uName;
    private String uPsw;
    private Integer uSex;
    private Long uAge;


    public Long getuId() {
        return uId;
    }

    public void setuId(Long uId) {
        this.uId = uId;
    }

    public String getuName() {
        return uName;
    }

    public void setuName(String uName) {
        this.uName = uName;
    }

    public String getuPsw() {
        return uPsw;
    }

    public void setuPsw(String uPsw) {
        this.uPsw = uPsw;
    }

    public Integer getuSex() {
        return uSex;
    }

    public void setuSex(Integer uSex) {
        this.uSex = uSex;
    }

    public Long getuAge() {
        return uAge;
    }

    public void setuAge(Long uAge) {
        this.uAge = uAge;
    }

    @Override
    public String toString() {
        return "Users{" +
        ", uId=" + uId +
        ", uName=" + uName +
        ", uPsw=" + uPsw +
        ", uSex=" + uSex +
        ", uAge=" + uAge +
        "}";
    }
}
