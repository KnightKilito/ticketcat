package com.tpm.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tpm.Utils.*;
import com.tpm.entity.JsonResult;
import com.tpm.entity.User;
import com.tpm.exception.MyException;
import com.tpm.exception.enums.StateEnums;
import com.tpm.service.UserService;
import com.tpm.service.WxService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/wxUser")
public class WxUserController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    UserService userService;

    @RequestMapping("/hello")
    @ResponseBody
    public String hello(){
        return "Hello";
    }

    @Resource
    private WxService wxService;
    @Resource
    private RedisCacheManager redisCacheManager;

    /**
     * 首页
     * @return
     */
    @GetMapping("")
    public String index(){
        return "index";
    }

    /**
     * Login页面
     * @return
     */
    @GetMapping("wxLogin")
    public String wxLogin(){
        return "wxLogin";
    }
    @GetMapping("aliPay")
    public String aliPay(){
        return "aliPay";
    }
    /**
     * 用于检测扫码和关注状态
     * @return
     */
    @PostMapping("/checkLogin")
    @ResponseBody
    public JsonResult checkLogin(String ticket){
        //如果redis中有ticket凭证则说明用户已扫码说明Login成功
        if(redisCacheManager.hasKey(ticket)){
            int uid =  Integer.parseInt(redisCacheManager.get(ticket).toString());
            User user = userService.getById(uid);
            if(user == null){
                throw new MyException(StateEnums.UNKNOWN_ERROR,"获取不到扫码登录用户的uid");
            }
            //此处获取的password为加密后的password，token生成用这个就行
            String token = TokenUtil.sign(new User(user.getUsername(),user.getPassword()));
            HashMap<String, Object> hs = new HashMap<>();
            hs.put("token", token);
            hs.put("uid", user.getUid());
            hs.put("nickname",user.getNickname());
            hs.put("avatar",user.getAvatar());
            hs.put("wxid",user.getWxid());
            logger.info("微信用户扫码登录成功：{}",hs);
            //扫码通过则删除
            redisCacheManager.delete(ticket);
            return new JsonResult(Integer.parseInt(String.valueOf(HttpStatus.OK.value())), "微信用户扫码登录成功", hs);

        }
        throw new MyException(StateEnums.CHECK_ERROR,"用户未关注或登录失败");
    }

    /**
     * 获取二维码参数
     * @return
     */
    @GetMapping("/getQrCode")
    @ResponseBody
    public Object getQrCode(){
        return wxService.getQrCode();
    }

    /**
     * Login成功跳转
     * @return
     */
    @GetMapping("/success")
    @ResponseBody
    public String loginSuccess(){
        return "Login成功";
    }

    /***
     * 微信服务器触发get请求用于检测签名
     * @return
     */
    @GetMapping("/wxAction")
    @ResponseBody
    public String handleWxCheckSignature(HttpServletRequest request){
        //todo 严格来说这里需要做签名验证,这里为了方便就不做了

        String echostr = request.getParameter("echostr");
        return echostr;
    }
    /**
     * 接收微信推送事件
     * @param request
     * @return
     */
    @PostMapping("/wxAction")
    @ResponseBody
    public JsonResult JsonResult(HttpServletRequest request){
        try {
            InputStream inputStream = request.getInputStream();

            Map<String, Object> map = XmlUtil.parseXML(inputStream);

            String userOpenId = (String) map.get("FromUserName");
            String event = (String) map.get("Event");
            if("subscribe".equals(event)){
                //自己生成的二维码不管是关注还是扫码都能取到ticket凭证,这里使用Ticket作为每次二维码的唯一标识
                String ticket = (String) map.get("Ticket");


                logger.info("用户关注:{}",userOpenId);

                // 获取openid判断用户是否存在,不存在则新增用户
                QueryWrapper<User> queryWrapper = new QueryWrapper<>();
                queryWrapper.eq(userOpenId != null, "wxid", userOpenId);
                User user = userService.getOne(queryWrapper);
                //如果用户不存在则自动注册
                if(user == null){
                    // 设置盐
                    String salt = UUIDUtil.getUUID();
                    // 设置密码加密
                    String s = MD5Util.md5("123456" + salt);
                    user = new User(userOpenId.substring(0,6),s);
                    user.setUsername(userOpenId);
                    user.setPassword(s);
                    //所有user相关的set操作应该等创建完user对象后set，不然报null错误
                    // user.setUsername(userOpenId);
                    // user.setPassword(s);
                    user.setSalt(salt);
                    String uname = userOpenId.substring(0,4);
                    user.setNickname("微信用户"+uname);
                    QueryWrapper<User> queryWrapper2 = new QueryWrapper<>();
                    queryWrapper2.eq(uname != null, "username", uname);
                    User user2 = userService.getOne(queryWrapper2);
                    if(user2!=null){
                        logger.info("微信用户：用户名"+uname+"已存在，将设置账户名为全id");
                        user.setUsername(userOpenId);
                    }else{
                        user.setUsername(uname);
                    }

                    // 设置用户默认头像
                    user.setAvatar(user.DEFAULT_AVATAR);
                    //设置账号绑定wxid
                    user.setWxid(userOpenId);
                    userService.save(user);
                    logger.info("微信用户自动注册成功：{}",user);
                    //重新获取对象，才能拿到uid
                    user = userService.getOne(queryWrapper);
                }
                //将当前登录用户的uid存入redis，同时以ticket作为判断成功关注的标志用于checkLogin校验
                redisCacheManager.set(ticket,user.getUid(),10*60);
                //此处获取的password为加密后的password，token生成用这个就行
                String token = TokenUtil.sign(new User(user.getUsername(),user.getPassword()));
                HashMap<String, Object> hs = new HashMap<>();
                hs.put("token", token);
                hs.put("uid", user.getUid());
                hs.put("nickname",user.getNickname());
                hs.put("avatar",user.getAvatar());
                hs.put("wxid",user.getWxid());
                logger.info("微信用户扫码登录成功：{}",hs);
                return new JsonResult(Integer.parseInt(String.valueOf(HttpStatus.OK.value())), "微信用户扫码登录成功", hs);
            }else if("SCAN".equals(event)){
                //自己生成的二维码不管是关注还是扫码都能取到ticket凭证
                String ticket = (String) map.get("Ticket");
                redisCacheManager.set(ticket,"",10*60);
                logger.info("用户扫码:{}",userOpenId);
            }
            logger.info("接收参数:{}",map);

        } catch (IOException e) {
            e.printStackTrace();
        }
        //return new JsonResult(Integer.parseInt(String.valueOf(HttpStatus.OK.value())), "登录成功");
        return new JsonResult(StateEnums.WXUSER_DOES_NOT_SUBSCRIBE.getCode(),"请关注该微信公众号以登录");
    }

    @PostMapping("wxxcxLogin/{wxopenid}/{wxUsername}")
    @ResponseBody
    public JsonResult wxxcxLogin(@PathVariable String wxopenid,@PathVariable String wxUsername){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(wxopenid != null, "wxid", wxopenid);
        User user = userService.getOne(queryWrapper);
        if(user == null){
            // 设置盐
            String salt = UUIDUtil.getUUID();
            // 设置密码加密
            String def_pas = "123456";
            String s = MD5Util.md5(def_pas + salt);
            user = new User(wxopenid,s);
            user.setNickname("微信小程序用户"+def_pas.substring(0,4));
            user.setUsername(wxopenid.substring(0,6));
            user.setPassword(s);
            // 设置用户默认头像
            user.setAvatar(user.DEFAULT_AVATAR);
            user.setWxid(wxopenid);
            user.setWxUsername(wxUsername);
            user.setSalt(salt);
            logger.info("微信小程序用户自动注册成功：{}",user);
            userService.save(user);
            queryWrapper.eq(wxopenid != null, "wxid", wxopenid);
            User users = userService.getOne(queryWrapper);
            //此处获取的password为加密后的password，token生成用这个就行
            String token = TokenUtil.sign(new User(user.getUsername(),user.getPassword()));
            HashMap<String, Object> hs = new HashMap<>();
            hs.put("token", token);
            hs.put("uid", users.getUid());
            hs.put("nickname",users.getNickname());
            hs.put("avatar",users.getAvatar());
            hs.put("wxid",users.getWxid());
            hs.put("wxusername",user.getWxUsername());
            return new JsonResult(StateEnums.WXXCXUSER_AUTO_REG_LOGIN.getCode(), "微信小程序用户自动注册登录成功", hs);
        }else{
            //此处获取的password为加密后的password，token生成用这个就行
            String token = TokenUtil.sign(new User(user.getUsername(),user.getPassword()));
            HashMap<String, Object> hs = new HashMap<>();
            hs.put("token", token);
            hs.put("uid", user.getUid());
            hs.put("nickname",user.getNickname());
            hs.put("avatar",user.getAvatar());
            hs.put("wxid",user.getWxid());
            hs.put("wxusername",user.getWxUsername());
            return new JsonResult(Integer.parseInt(String.valueOf(HttpStatus.OK.value())), "微信小程序用户登录成功", hs);
        }


    }
}
