package com.tpm.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.util.Date;
@Data
@TableName(value = "movie")
public class Movie {
    @TableId(type = IdType.NONE)//雪花算法自动生成id
    private Integer mid;

    @NotNull(message = "电影名称不能为空")
    private String mname;

    @NotNull(message = "电影介绍不能为空")
    private String context;

    private String score;

    @NotNull(message = "电影导演不能为空")
    private String director;

    @NotNull(message = "电影演员不能为空")
    private String actor;

    @NotNull(message = "电影标签不能为空")
    private String label;

    @NotNull(message = "电影上映日期不能为空")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date rtime;

    @NotNull(message = "电影时长不能为空")
    private String duration;

    @NotNull(message = "电影建议零售价不能为空")
    private Double money;

    private String img;
    private int status;
    private String style;

}