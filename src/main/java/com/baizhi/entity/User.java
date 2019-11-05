package com.baizhi.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;
@Data
@Accessors(chain = true)
public class User implements Serializable {


    @Id
    private String id;
    private String username;
    private String salt;
    private String password;
    private String phone;
    private String nickname;
    private String province;
    private String city;
    private String sign;
    private String photo;
    private String sex;
    private String starId;
    @JSONField(format = "yyyy-MM-dd")

    private Date createDate;

    public void setStar_id(String star_id){
       this.starId=star_id;
    }

    public void setCreate_date(Date create_date){
        this.createDate=create_date;
    }
}
