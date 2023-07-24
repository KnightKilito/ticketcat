package com.tpm.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@TableName(value = "session")
public class Session {
    @TableId(type = IdType.AUTO)
    private Integer sid;
    private Integer cid;
    private Integer mid;

    @NotNull(message = "影票价格不能为空")
    @Min(value = 0, message = "影票价格不能小于0")
    private double tprice;
    @NotNull(message = "容纳人数不能为空")
    @Max(value = 100,message = "根据疫情防控规则，单场人数最多不超过100")
    private Integer maxsize;
    private Integer orderednum;
    @NotNull(message = "放映开始时间不能为空")
    private String starttime;
    private String endtime;
    //放映厅号hallId
    @NotNull(message = "放映厅号不能为空")
    private Integer hid;
    private String date;

}