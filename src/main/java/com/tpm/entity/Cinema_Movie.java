package com.tpm.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value = "cinema_movie")
public class Cinema_Movie {

    Integer cid;

    Integer mid;
    @TableId
    Integer cmid;

}
