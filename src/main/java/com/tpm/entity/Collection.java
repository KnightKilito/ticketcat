package com.tpm.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@TableName(value = "collection")
@Data
public class Collection {
    @TableId(type = IdType.NONE)
    int collectionid;
    int uid;
    int mid;
}
