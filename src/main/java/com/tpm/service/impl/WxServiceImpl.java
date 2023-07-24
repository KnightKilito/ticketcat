package com.tpm.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tpm.Utils.RedisCacheManager;
import com.tpm.service.WxService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Component
@Service
//@PropertySource(value = "classpath:application.yml")
public class WxServiceImpl implements WxService {

    Logger logger = LoggerFactory.getLogger(WxServiceImpl.class);

    @Value("${wx.appid}")
    private String appid;
    @Value("${wx.secret}")
    private String secret;
    @Resource
    private RestTemplate restTemplate;
    @Resource
    private RedisCacheManager redisCacheManager;

    @Override
    public String getAccessToken() {
        String key = "wx_access_token";

        //从redis缓存中获取token
        if(redisCacheManager.hasKey(key)){
            return (String) redisCacheManager.get(key);
        }

        String url = String.format("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=%s&secret=%s",appid,secret);

        ResponseEntity<String> result = restTemplate.getForEntity(url, String.class);

        if(result.getStatusCode()== HttpStatus.OK){

            JSONObject jsonObject = JSON.parseObject(result.getBody());

            String access_token = jsonObject.getString("access_token");
            Long expires_in = jsonObject.getLong("expires_in");
            //缓存toekn到redis
            redisCacheManager.set(key,access_token,expires_in);
            return access_token;
        }
        return null;
    }

    @Override
    public Map<String, Object> getQrCode() {
        //获取临时二维码
        String url = String.format("https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=%s",getAccessToken());
        ResponseEntity<String> result = restTemplate.postForEntity(url, "{\"expire_seconds\": 604800, \"action_name\": \"QR_STR_SCENE\", \"action_info\": {\"scene\": {\"scene_str\": \"test\"}}}", String.class);

        logger.info("二维码:{}",result.getBody());

        JSONObject jsonObject = JSON.parseObject(result.getBody());
        Map<String,Object> map=new HashMap<>();
        map.put("ticket",jsonObject.getString("ticket"));
        map.put("url",jsonObject.getString("url"));

        return map;
    }
}