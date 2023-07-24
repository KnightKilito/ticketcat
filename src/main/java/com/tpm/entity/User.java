package com.tpm.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@TableName(value = "user")
@Accessors(chain = true)
@NoArgsConstructor
public class User implements Serializable {
    private static final long serialVersionUID=1L;

    public static final String DEFAULT_AVATAR = "https://tpm-avatar.moechun.fun/default/%E7%8C%AB%E5%92%AA.png";

    @TableId(type = IdType.NONE)//雪花算法自动生成id
    private Integer uid;

    @NotNull(message = "用户名不能为空")
    private String username;

    @NotNull(message = "密码不能为空")
    private String password;

    private String oldPassword;

    @NotNull(message = "电话号码不能为空")
    private String phone;

    private String nickname;

    private String avatar;

    private Integer status;

    private Integer tec;

    private Integer love;

    private Integer cartoon;

    @NotNull(message = "用户身份不能为空")
    private String identity;

    /**
     * 加密盐值
     */
    private String salt;

  
    private String wxid;
    private String wxUsername;

    public User(String username, String s) {
    }
}