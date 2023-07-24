package com.tpm.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;
@Data
@TableName(value = "ticket")
public class Ticket {
    @TableId(type = IdType.AUTO)
    private Integer tid;

    private Integer mid;

    private Integer uid;

    private Integer cid;

    private Integer status;

    private Date ordertime;

    private String outtime;
    @NotNull(message = "sid不能为空")
    private Integer sid;

    private String sessionseats;
    private String sessionseatids;
    private double tmoney;
    private String tcontext;

}