package com.tpm.service;

import java.util.Map;

public interface WxService {
    //获取token
    String getAccessToken();
    //获取生成二维码参数
    Map<String,Object> getQrCode();
}
