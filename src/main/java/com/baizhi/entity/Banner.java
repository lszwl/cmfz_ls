package com.baizhi.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

@Data
@Accessors(chain = true)
public class Banner implements Serializable {
    @Id
    private String id;
    private String bannerName;
    private String cover;
    private String description;
    private String status;
   // @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Date createDate;


    public void setBanner_name(String banner_name ){
        this.bannerName=banner_name;
    }
    public void setCreate_date(Date create_dat ){
        this.createDate=create_dat;
    }
}
