package com.tpm.Utils;


import com.alibaba.fastjson.JSON;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class QiniuUtils {

    public static  final String url = "https://tpm-avatar.moechun.fun/";  //七牛云图片服务器测试域名（有效1个月）

    @Value("${qiniu.accessKey}")
    private  String accessKey;      //类似账号，在七牛云个人空间---密钥管理获取
    @Value("${qiniu.accessSecretKey}")
    private  String accessSecretKey;   //类似密码

    public boolean upload(MultipartFile file, String fileName){

        //构造一个带指定 Region 对象的配置类
        Configuration cfg = new Configuration(Region.huanan());  //根据自己的对象空间的地址选（华南）
        //...其他参数参考类注释
        UploadManager uploadManager = new UploadManager(cfg);
        //...生成上传凭证，然后准备上传 (个人存储空间名字)
        String bucket = "ticketcat";//
        //默认不指定key的情况下，以文件内容的hash值作为文件名
        try {
            byte[] uploadBytes = file.getBytes();
            Auth auth = Auth.create(accessKey, accessSecretKey);
            String upToken = auth.uploadToken(bucket);
            Response response = uploadManager.put(uploadBytes, fileName, upToken);
            //解析上传成功的结果
            DefaultPutRet putRet = JSON.parseObject(response.bodyString(), DefaultPutRet.class);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }
}

