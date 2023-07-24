package com.tpm.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@TableName(value = "hall")
public class Hall {
    @TableId(type = IdType.AUTO)
    private Integer hid;

    private String hname;

    private Integer cid;

    @NotNull(message = "影厅座位排数不能为空")
    private Integer rowsize;

    @NotNull(message = "影厅座位列数不能为空")
    private Integer columnsize;
}
