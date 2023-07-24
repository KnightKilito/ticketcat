package com.tpm.controller;

import com.tpm.Utils.QiniuUtils;
import com.tpm.entity.JsonResult;
import com.tpm.exception.enums.StateEnums;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
*@BelongsProject: TicketCat
*@BelongsPackage: com.tpm.controller
*@Author: wuhzh
*@CreateTime: 2023-07-21 10:46
*@Description: 阿里低代码引擎的后端服务
*@Version: 1.0
*/
@Slf4j
@RestController
@RequestMapping("/lowCode")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class LowCodeEngineController {

    private final QiniuUtils qiniuUtils ;

    /**
     * 文件上传
     * @param file 上传的文件
     * @return JsonResult
     */
    @PostMapping("uploadSchema")
    public JsonResult<?> uploadSchema(@RequestParam MultipartFile file){
        //原始文件名称比如 aa.png
        String originalFilename = file.getOriginalFilename() ;
        //将原始名称修改为：唯一文件名称
//        String fileName = UUID.randomUUID() + "." + StringUtils.substringAfterLast(originalFilename, ".");
        boolean upload = qiniuUtils.upload(file, originalFilename);
        if (upload){
            //上传成功
            String schemaUrl = QiniuUtils.url+ originalFilename;
            log.info("====upUrl:"+schemaUrl+"====") ;
            return new JsonResult<>(StateEnums.SUCCESS.getCode(),"上传成功",schemaUrl);
        }
        return new JsonResult<>(StateEnums.UNKNOWN_ERROR.getCode(),"上传失败");
    }
}
