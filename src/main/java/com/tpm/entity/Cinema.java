package com.tpm.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@TableName(value = "cinema")
public class Cinema {
    @TableId(type = IdType.NONE)//雪花算法自动生成id
    Integer cid;
    @NotNull(message="影院名字不能为空")
    String cname;
    @NotNull(message="影院地址不能为空")
    String address;
    Integer status;
    @NotNull(message="影院品牌不能为空")
    String brand;
    String label;

}
