package com.tpm.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tpm.entity.*;
import com.tpm.exception.MyException;
import com.tpm.exception.enums.StateEnums;
import com.tpm.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/ticket")
public class TicketController {
    @Autowired
    TicketService ticketService;

    @Autowired
    MovieService movieService;

    @Autowired
    SessionService sessionService;

    @Autowired
    SessionSeatService sessionSeatService;

    @Autowired
    CinemaService cinemaService;

    //未付款 0，已付款 1，待评价2，已评价 3

    //添加订单
    //别用
    @PostMapping("adwawdawdadazcawxacwaawddascxawda")
    public JsonResult SaveTicket(@Valid Ticket ticket, BindingResult br){
        if(br.hasErrors()){
            throw new MyException(StateEnums.ADD_ERROR,br.getFieldError().getDefaultMessage());

        }else{
            //设置订票时间
            ticket.setOrdertime(new Date());
            //设置
            ticket.setOuttime(sessionService.getById(ticket.getSid()).getEndtime());
            ticket.setStatus(1);
            ticketService.save(ticket);
            return new JsonResult("添加成功");
        }
    }

    //用这个
    //添加订单，同时修改sessionseat的状态
    @PostMapping("/addTicket/{uid}")
    public JsonResult addTicketAndSetSatus(@RequestBody List<SessionSeat> sessionSeatsList, @PathVariable int uid){

//        System.out.println(arr);
//            sessionSeatService.list
      //  String s2 = arr.replaceAll("//[","");

//        String[] s=arr.replaceAll("\\[","").replaceAll("]","").split("\"");
//        String[] s2 =s[s.length-2].split(",");
//        for (String s1 : s2) {
//            System.out.println(s1);
//        }


        for (SessionSeat sessionSeat : sessionSeatsList) {
            System.out.println(sessionSeat);
        }

        Ticket ticket = new Ticket();
        for (SessionSeat sessionSeat : sessionSeatsList) {
            sessionSeat.setUid(uid);
            sessionSeat.setStatus(2);
            boolean isU = sessionSeatService.updateById(sessionSeat);
            if (isU) {

                if (ticket.getSessionseats()==null) {
                    ticket.setSessionseats(sessionSeat.getRowname() + "排" + sessionSeat.getColumnname() + "座");
                    ticket.setSessionseatids(String.valueOf(sessionSeat.getSessionseatid()));
                } else {
                    ticket.setSessionseats(ticket.getSessionseats() + "," + sessionSeat.getRowname() + "排" + sessionSeat.getColumnname() + "座");
                    ticket.setSessionseatids(ticket.getSessionseatids()+","+sessionSeat.getSessionseatid());
                }

            } else {
                throw new MyException(StateEnums.EDIT_ERROR, "更新座位状态异常");
            }
        }


        //添加ticket条件
        ticket.setSid(sessionSeatsList.get(0).getSid());
        ticket.setUid(uid);
        ticket.setCid(sessionService.getById(sessionSeatsList.get(0).getSid()).getCid());
        ticket.setMid(sessionService.getById(sessionSeatsList.get(0).getSid()).getMid());
        ticket.setOrdertime(new Date());
        ticket.setOuttime(sessionService.getById(sessionSeatsList.get(0).getSid()).getEndtime());
        ticket.setStatus(1);
        ticket.setTmoney(sessionService.getById(sessionSeatsList.get(0).getSid()).getTprice()*sessionSeatsList.size());
        boolean isA = ticketService.save(ticket);
        if(isA){
            return new  JsonResult("添加成功");
        }else{
            throw new MyException(StateEnums.ADD_ERROR,"添加订单中出现异常");
        }

        //迭代器，狗都不用
        //   Iterator<SessionSeat> iterator = sessionSeatsList.iterator();

//        while(iterator.hasNext()) {
//            iterator.next().setStatus(2);
//            iterator.next().setUid(uid);
//            //      System.out.println(iterator.next());
//            boolean isU = sessionSeatService.updateById(iterator.next());
//            if (isU) {
//
//                if (ticket.getSessionseats().length() == 0) {
//                    ticket.setSessionseats(iterator.next().getRowname() + "排" + iterator.next().getColumnname() + "座");
//
//                } else {
//                    ticket.setSessionseats(ticket.getSessionseats() + "," + iterator.next().getRowname() + "排" + iterator.next().getColumnname() + "座");
//                }
//
//            } else {
//                throw new MyException(StateEnums.EDIT_ERROR, "更新状态异常0");
//            }
        //       }

    }
    //小程序端
    @PostMapping("/addTicketByWX/{uid}")
    public JsonResult at(@RequestBody String arr,@PathVariable int uid){
        //切割数组
        String[] s=arr.replaceAll("\\[","").replaceAll("]","").split("\"");
        String[] s2 =s[s.length-2].split(",");
        List<Integer> list = new ArrayList<>();
        for (String s1 : s2) {
            list.add(Integer.valueOf(s1));
        }
        List<SessionSeat> sessionSeatsList = sessionSeatService.listByIds(list);

        Ticket ticket = new Ticket();
        for (SessionSeat sessionSeat : sessionSeatsList) {
            sessionSeat.setUid(uid);
            sessionSeat.setStatus(2);
            boolean isU = sessionSeatService.updateById(sessionSeat);
            if (isU) {

                if (ticket.getSessionseats()==null) {
                    ticket.setSessionseats(sessionSeat.getRowname() + "排" + sessionSeat.getColumnname() + "座");
                    ticket.setSessionseatids(String.valueOf(sessionSeat.getSessionseatid()));
                } else {
                    ticket.setSessionseats(ticket.getSessionseats() + "," + sessionSeat.getRowname() + "排" + sessionSeat.getColumnname() + "座");
                    ticket.setSessionseatids(ticket.getSessionseatids()+","+sessionSeat.getSessionseatid());
                }

            } else {
                throw new MyException(StateEnums.EDIT_ERROR, "更新座位状态异常");
            }
        }


        //添加ticket条件
        ticket.setSid(sessionSeatsList.get(0).getSid());
        ticket.setUid(uid);
        ticket.setCid(sessionService.getById(sessionSeatsList.get(0).getSid()).getCid());
        ticket.setMid(sessionService.getById(sessionSeatsList.get(0).getSid()).getMid());
        ticket.setOrdertime(new Date());
        ticket.setOuttime(sessionService.getById(sessionSeatsList.get(0).getSid()).getEndtime());
        ticket.setStatus(1);
        ticket.setTmoney(sessionService.getById(sessionSeatsList.get(0).getSid()).getTprice()*sessionSeatsList.size());
        boolean isA = ticketService.save(ticket);
        if(isA){
            return new  JsonResult("添加成功");
        }else{
            throw new MyException(StateEnums.ADD_ERROR,"添加订单中出现异常");
        }
    }

    //失败状态-未支付订单，但是锁定座位
    @PostMapping("addTicketButNoMoney/{uid}")
    public JsonResult addTicketAndSetSatusAndNoPay(@RequestBody List<SessionSeat> sessionSeatsList, @PathVariable int uid){
        Ticket ticket = new Ticket();
        for (SessionSeat sessionSeat : sessionSeatsList) {
            sessionSeat.setUid(uid);
            sessionSeat.setStatus(2);
            boolean isU = sessionSeatService.updateById(sessionSeat);
            if (isU) {
                if (ticket.getSessionseats()==null) {
                    ticket.setSessionseats(sessionSeat.getRowname() + "排" + sessionSeat.getColumnname() + "座");
                    ticket.setSessionseatids(String.valueOf(sessionSeat.getSessionseatid()));
                } else {
                    ticket.setSessionseats(ticket.getSessionseats() + "," + sessionSeat.getRowname() + "排" + sessionSeat.getColumnname() + "座");
                    ticket.setSessionseatids(ticket.getSessionseatids()+","+sessionSeat.getSessionseatid());
                }

            } else {
                throw new MyException(StateEnums.EDIT_ERROR, "更新状态异常,查看数据库是否字段存在");
            }
        }

        //   Iterator<SessionSeat> iterator = sessionSeatsList.iterator();

//        while(iterator.hasNext()) {
//            iterator.next().setStatus(2);
//            iterator.next().setUid(uid);
//            //      System.out.println(iterator.next());
//            boolean isU = sessionSeatService.updateById(iterator.next());
//            if (isU) {
//
//                if (ticket.getSessionseats().length() == 0) {
//                    ticket.setSessionseats(iterator.next().getRowname() + "排" + iterator.next().getColumnname() + "座");
//
//                } else {
//                    ticket.setSessionseats(ticket.getSessionseats() + "," + iterator.next().getRowname() + "排" + iterator.next().getColumnname() + "座");
//                }
//
//            } else {
//                throw new MyException(StateEnums.EDIT_ERROR, "更新状态异常0");
//            }
        //       }

        //添加ticket条件
        ticket.setSid(sessionSeatsList.get(0).getSid());
        ticket.setUid(uid);
        ticket.setCid(sessionService.getById(sessionSeatsList.get(0).getSid()).getCid());
        ticket.setMid(sessionService.getById(sessionSeatsList.get(0).getSid()).getMid());
        ticket.setOrdertime(new Date());
        ticket.setOuttime(sessionService.getById(sessionSeatsList.get(0).getSid()).getEndtime());
        ticket.setTmoney(sessionService.getById(sessionSeatsList.get(0).getSid()).getTprice()*sessionSeatsList.size());
        ticket.setStatus(0);
        int tid = Math.abs((int) System.currentTimeMillis());
        ticket.setTid(tid);
        ticket.setTcontext("这个是"+ticket.getSessionseats());
        boolean isA = ticketService.save(ticket);
        System.out.println("==============走到这了==================");
        if(isA){
            Ticket tt = ticketService.getById(tid);
            return new JsonResult(tt);//"成功创建订单，等待支付",
//            return "/alipay/pay";
        }else{
            throw new MyException(StateEnums.ADD_ERROR,"添加订单中出现异常");
        }

    }


    //删除订单
    @DeleteMapping("/{tid}")
    public JsonResult DelTicket(@PathVariable int tid){
        //先找到tid-->随后找到t对象-->随后释放座位
        Ticket t = ticketService.getById(tid);
        String seatids = t.getSessionseatids();
        String[] sArray = seatids.split(",");
        for(String s:sArray){
            SessionSeat ss=sessionSeatService.getById(Integer.valueOf(s));
            ss.setStatus(0);
            ss.setVersion(1);
            ss.setUid(null);
            sessionSeatService.updateById(ss);
        }
        boolean isDel = ticketService.removeById(tid);
        if(isDel){
            return new JsonResult("删除成功");
        }else{
            throw  new MyException(StateEnums.REMOVE_ERROR,"删除异常");
        }
    }

    //根据查看用户偏好，并返回对应的map和count数据，例如:<科幻，0.333><喜剧,0.67> 总数3
    @GetMapping("/favorite/{uid}")
    public JsonResult findMyFavorite(@PathVariable int uid){
        Map<String,Object> map = new HashMap<>();
        map.put("uid",uid);
        List<Ticket> ticketList = ticketService.listByMap(map);
        Map<String,Double> favoriteMap = new HashMap<>();
        long count = ticketList.size();
        for(Ticket t:ticketList){
            Movie movie = movieService.getById(t.getMid());
            if(favoriteMap.containsKey(movie.getLabel())){
                double old = favoriteMap.get(movie.getLabel());
                double New = old+1;
                favoriteMap.put(movie.getLabel(),New);
            }
            else {
                favoriteMap.put(movie.getLabel(), 1.0);
            }
        }
//        Iterator<Map.Entry<String, Integer>> entries = favoriteMap.entrySet().iterator();
//        while (entries.hasNext()) {
//
//            Map.Entry<String, Integer> entry = entries.next();
//            entry.setValue()
//
//            //System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
//
//        }
        for(String v:favoriteMap.keySet()){
            double f=  favoriteMap.get(v)/count;
            BigDecimal b = new BigDecimal(f);
            double f1 = b.setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
            favoriteMap.put(v,f1);
        }
        return new JsonResult(count, (HashMap) favoriteMap);
    }

    //查找订单
    @GetMapping("")
    public JsonResult<Ticket> findAllTicket(){
        List<Ticket> records = ticketService.list();
        long total = records.size();
        return new JsonResult<Ticket>(total,records);
    }
    //根据用户查找订单
    @GetMapping("getTicket/{uid}")
        public JsonResult getTicketByUid(@PathVariable int uid){
        Map<String,Object> map = new HashMap<>();
        map.put("uid",uid);
        List<Object> list = new ArrayList<>();
        List<Ticket> ticketList = ticketService.listByMap(map);


        for(Ticket t1:ticketList){
            //判断是否超过一分钟
            SimpleDateFormat sdf =   new SimpleDateFormat( "mm" );
            int nowTime = Integer.valueOf(sdf.format(new Date()));
            int ticketTime = Integer.valueOf(sdf.format(t1.getOrdertime()));
            //时间超过一分钟，且状态不属于已评价
            if(nowTime!=ticketTime&&t1.getStatus()!=3){
                t1.setStatus(2);
                ticketService.updateById(t1);
            }
        }

        //查询更新后的数组
        List<Ticket> ticketList2 = ticketService.listByMap(map);
        for(Ticket t:ticketList2){
            Map<String,Object> map1 = new HashMap<>();
            map1.put("tid",t.getTid());
            map1.put("status",t.getStatus());
            map1.put("ordertime",t.getOrdertime());
            map1.put("starttime",sessionService.getById(t.getSid()).getStarttime());
            map1.put("outtime",t.getOuttime());
            map1.put("date",sessionService.getById(t.getSid()).getDate());
            map1.put("cname",cinemaService.getById(t.getCid()).getCname());
            map1.put("mname",movieService.getById(t.getMid()).getMname());
            map1.put("tmoney",t.getTmoney());
            map1.put("sessionseats",t.getSessionseats());
            map1.put("address",cinemaService.getById(t.getCid()).getAddress());
            map1.put("label",movieService.getById(t.getMid()).getLabel());
            map1.put("duration",movieService.getById(t.getMid()).getDuration());
            map1.put("img",movieService.getById(t.getMid()).getImg());
            map1.put("mid",t.getMid());
            list.add(map1);
        }

        return new JsonResult<>(list);
    }

    //分页查询
    @GetMapping("{current}/{limit}")
    public JsonResult<Ticket> page(@PathVariable long current,@PathVariable long limit){
        Page<Ticket> objectPage = new Page<>(current,limit);
        ticketService.page(objectPage,null);
        long total = objectPage.getTotal();
        List<Ticket> records = objectPage.getRecords();
        return new JsonResult<Ticket>(total,records);
    }

    @GetMapping("test")
    public String date(){
        Ticket t = ticketService.getById(1736548353);
        Date d = t.getOrdertime();
        SimpleDateFormat sdf =   new SimpleDateFormat( "mm" );
        String d1=sdf.format(d);
        String d2 = sdf.format(new Date());
        System.out.println(d1+"-----"+d2);
        return d1+d2;
    }

}
