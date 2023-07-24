package com.tpm.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.Version;
import lombok.Data;

@Data
@TableName(value = "sessionseat")
public class SessionSeat {
    @TableId(type = IdType.AUTO)
    private Integer sessionseatid;

    private Integer sid;
    private Integer hid;
    private Integer status;
    private Integer uid;

    private Integer rowname;

    private Integer columnname;

    @Version//用于乐观锁
    private Integer version;

}
