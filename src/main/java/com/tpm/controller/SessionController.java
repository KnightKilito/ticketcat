package com.tpm.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tpm.entity.*;
import com.tpm.exception.MyException;
import com.tpm.exception.enums.StateEnums;

import com.tpm.service.HallService;
import com.tpm.service.MovieService;
import com.tpm.service.SessionSeatService;
import com.tpm.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/session")
public class SessionController {
    @Autowired
    SessionService sessionService;
    @Autowired
    HallService hallService;
    @Autowired
    SessionSeatService sessionSeatService;
    @Autowired
    MovieService movieService;


    public boolean isStatus(int nowRow,int nowClu,int row,int clu){

        if(nowRow>(row/2)-1&&nowRow<=(row/2)+2&&nowClu>=(clu/2)-1&&nowClu<=(clu/2)+2){
            return true;
        }
        return false;

    }
    //娣诲姞鍦烘�′俊鎭�锛屽悓鏃舵坊鍔犲骇浣嶈〃
    @PostMapping("")
    public JsonResult addSession(@Valid Session session, BindingResult br){
            if(br.hasErrors()){
            throw new MyException(StateEnums.CHECK_ERROR,br.getFieldError().getDefaultMessage());
        }else{
                
          //鍔犲叆flag鍒ゆ柇 娣诲姞sesstonseat鏃犺��
          int flag = 0;
          //鍒ゆ柇鏃堕棿
          String startt = session.getStarttime();
          int duration = Integer.parseInt(movieService.getById(session.getMid()).getDuration());
          String[] sArray = startt.split(":");
          int hour = Integer.parseInt(sArray[0]);
          int minute = Integer.parseInt(sArray[1]);
          int sum = hour*60+minute+duration;
          String h = String.valueOf((sum/60));
          String m = String.valueOf(sum%60);
          if(m.length()==1){
              m="0"+m;
          }
          String endt = h+":"+m;
          session.setEndtime(endt);

          Hall hall = hallService.getById(session.getHid());
          boolean isAdd =  sessionService.save(session);
                //鏌ユ壘鍒板垰鍒氭彃鍏ョ殑閭ｄ釜session鐨剆id锛屾潵鍏宠仈sessionseat
          QueryWrapper<Session> queryWrapper = new QueryWrapper<>();
          queryWrapper.eq("cid",session.getCid());
          queryWrapper.eq("mid",session.getMid());
          queryWrapper.eq("starttime",session.getStarttime());
          queryWrapper.eq("endtime",session.getEndtime());
          queryWrapper.eq("date",session.getDate());
          queryWrapper.eq("hid",session.getHid());
          try{
              Session session1 = sessionService.getOne(queryWrapper);
          }catch (Exception e){
              sessionService.remove(queryWrapper);
              throw  new MyException(StateEnums.FIND_ERROR,"鍦烘�″凡缁忓瓨鍦�");
          }


          //寰�鐜�鐢熸垚绌虹殑搴т綅鏁版嵁搴撹〃
           for(int i= 1;i<=hall.getRowsize();i++){
               for(int j=1;j<=hall.getColumnsize();j++){
                   SessionSeat s = new SessionSeat();
                   s.setSid(session.getSid());
                   s.setHid(session.getHid());
                   s.setColumnname(j);
                   s.setRowname(i);
                   if(isStatus(i,j,hall.getRowsize(),hall.getColumnsize())){
                       s.setStatus(1);
                   }else{
                       s.setStatus(0);
                   }

                   s.setSid(session.getSid());
                   s.setVersion(1);
                  boolean is= sessionSeatService.save(s);
                  if(is!=true){
                      flag++;
                  }
               }
           }

           if(isAdd&&flag==0){
               return new  JsonResult("娣诲姞鎴愬姛");
           }
           else{
               return  new JsonResult(-1,"娣诲姞澶辫触");
           }
        }
    }
    //鍒犻櫎鍦烘��
    @DeleteMapping("{sid}")
    public JsonResult delSession(@PathVariable int sid){

            boolean removeByUid = sessionService.removeById(sid);
            if (removeByUid ) {
                QueryWrapper queryWrapper = new QueryWrapper();
                queryWrapper.eq("sid",sid);
                List<SessionSeat> sessionSeatList = sessionSeatService.list(queryWrapper);
                sessionSeatService.removeBatchByIds(sessionSeatList);
                return new JsonResult("删除成功");
            } else {
                return new JsonResult(-1,"失败");
            }

    }
    //淇�鏀瑰満娆′俊鎭�
    //涓嶇敤
    @PutMapping("")
    public JsonResult updateSession(Session session,BindingResult br){
        if (br.hasErrors()){
            throw new MyException(StateEnums.CHECK_ERROR,br.getFieldError().getDefaultMessage());
        }else {
            boolean isUpdate=sessionService.updateById(session);
            if(isUpdate==true){
                return new JsonResult("淇�鏀规垚鍔�");
            }
            else{
                return new JsonResult(-1,"淇�鏀瑰け璐�");
            }
        }
    }


    //鏍规嵁褰辩墖鏌ヨ�㈠満娆�
    @GetMapping("mid/{mid}")
    public JsonResult<Session> findByMid(@PathVariable int mid){

            Map<String,Object> map = new HashMap<>();
            map.put("mid",mid);
            return new JsonResult<Session>(sessionService.listByMap(map));

    }
    //瀹屾垚
    //鏍规嵁褰遍櫌鍜屽奖鐗囨煡璇㈠満娆�
    @GetMapping("{cid}/{mid}")
    public JsonResult<Session> findByCid(@PathVariable int cid,@PathVariable int mid){

            Map<String,Object> map = new HashMap<>();
            map.put("mid",mid);
            map.put("cid",cid);
            List<Session> sessionList = sessionService.listByMap(map);
            //杩斿洖鏃ユ湡鏁扮粍
             int i= 0;
             int flag=sessionList.size();
            String[] sArray = new String[sessionList.size()];
            for(Session s:sessionList){
                if(!Arrays.asList(sArray).contains(s.getDate())){
                    sArray[i]=s.getDate();
                    i++;
                    flag--;
                }

            }
            String[] sArrayt = new String[sArray.length-flag];
            for(int j=0;j<sArrayt.length;j++){
                sArrayt[j]=sArray[j];
            }

            return new JsonResult<Session>(sArrayt,sessionList);

    }

    //鏍规嵁褰遍櫌鏌ョ湅褰遍櫌鍐呯殑鍦烘��
    @GetMapping("cid/{cid}")
    public JsonResult<Session> findByCid(@PathVariable int cid){

        Map<String,Object> map = new HashMap<>();
        map.put("cid",cid);
        return new JsonResult<Session>(sessionService.listByMap(map));

    }

    //鏍规嵁鐢靛奖鍜屽奖闄�-->杩斿洖鍏ㄩ儴鐨勫満娆℃椂闂�
    @GetMapping("/{cid}/{mid}/{date}")
    public JsonResult findByDate(@PathVariable int cid,@PathVariable int mid ,@PathVariable String date){
        Map<String,Object> map = new HashMap<>();
        map.put("mid",mid);
        map.put("cid",cid);
        map.put("date",date);
        List<Session> sessionList = sessionService.listByMap(map);
        for (Session session : sessionList) {
            int flag=0;
            //取对应的sessionseat数量
            QueryWrapper queryWrapper = new QueryWrapper();
            queryWrapper.eq("sid",session.getSid());
            List<SessionSeat> sessionSeatList = sessionSeatService.list(queryWrapper);
            //循环sessionseatList
            for (SessionSeat sessionSeat : sessionSeatList) {
                if(sessionSeat.getStatus()==2){
                    flag++;
                }
            }
            session.setOrderednum(flag);
        }
        return new JsonResult<Session>(sessionList);
    }

    //鍒嗛〉鏌ヨ��
    @GetMapping("/pages/{current}/{limit}")
    public JsonResult<Session> page(@PathVariable long current,@PathVariable long limit){
        Page<Session> objectPage = new Page<>(current,limit);
        sessionService.page(objectPage,null);
        long total = objectPage.getTotal();
        List<Session> records = objectPage.getRecords();
        return new JsonResult<Session>(total,records);
    }

}
