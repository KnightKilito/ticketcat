package com.tpm.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value = "moviecomment")
public class MovieComment {
    @TableId(type = IdType.NONE)//雪花算法自动生成id
    private Integer mcid;


    private Integer tid;



    private String comment;

    private Double uscore;

    private Integer mid;


}