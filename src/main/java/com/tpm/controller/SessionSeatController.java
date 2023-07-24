package com.tpm.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tpm.entity.JsonResult;
import com.tpm.entity.SessionSeat;
import com.tpm.service.SessionSeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("sessionseat")
public class SessionSeatController {
    @Autowired
    SessionSeatService sessionSeatService;

    //根据sid，返回全部座位的信息状态
    @GetMapping("/{sid}")
    public JsonResult<SessionSeat> findAllSessionSeat(@PathVariable int sid){
        QueryWrapper<SessionSeat> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("sid",sid);
        return new JsonResult<SessionSeat> (sessionSeatService.list(queryWrapper));

    }

    //根据传回的对象集合
    @PutMapping("/status")
    public JsonResult updateSessioSeatStatus(List<SessionSeat> list){
        int flag=0;
        for(SessionSeat s:list){
            SessionSeat sessionSeat=sessionSeatService.getById(s.getSessionseatid());

            sessionSeat.setStatus(2);

            boolean isup=sessionSeatService.updateById(sessionSeat);
            if(!isup){
                flag++;
            }
        }
        if(flag==0){
            return new JsonResult("修改成功");
        }else{
            return new JsonResult(-1,"修改失败");
        }


    }



}
