package com.baizhi.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

@Data
@Accessors(chain = true)
public class Star implements Serializable {

      @Id
      private String id;
      private String nickname;
      private String realname;
      private String photo;
      private String sex;
      private String status;
      @JSONField(format = "yyyy-MM-dd")
      @DateTimeFormat(pattern = "yyyy-MM-dd")
      private Date createDate;

      public void setCreate_date(Date create_date){
          this.createDate = create_date;
      }


}
