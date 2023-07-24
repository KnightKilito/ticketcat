package com.tpm.dto;

import lombok.Data;

@Data
public class UserDTO {
    private Integer uid;
    private String username;
    private String avatar;
    private String nickname;
    private String phone;
    private String wxid;

    public UserDTO(Integer uid, String username, String avatar, String nickname, String phone, String wxid) {
        this.uid = uid;
        this.username = username;
        this.avatar = avatar;
        this.nickname = nickname;
        this.phone = phone;
        this.wxid = wxid;
    }
}
