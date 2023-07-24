package com.tpm.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tpm.Utils.MD5Util;
import com.tpm.Utils.QiniuUtils;
import com.tpm.Utils.TokenUtil;
import com.tpm.Utils.UUIDUtil;
import com.tpm.dto.UserDTO;
import com.tpm.entity.JsonResult;
import com.tpm.entity.User;
import com.tpm.exception.MyException;
import com.tpm.exception.enums.StateEnums;
import com.tpm.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FileUtils;
//import sun.misc.BASE64Decoder;
//import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
//import sun.misc.BASE64Decoder;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.File;
//import java.io.FileOutputStream;
//import java.io.OutputStream;
//import java.nio.file.Files;
//import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final UserService userService;

    //用户登录
    @PostMapping("login")
    @ResponseBody
    public JsonResult<?> login(@RequestBody User user) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(user.getUsername() != null, "username", user.getUsername());
        User users = userService.getOne(queryWrapper);
        // 通过用户名从数据库中查询出该用户
        if (users == null) {
            throw new MyException(StateEnums.USER_DOES_NOT_EXISTS, "用户不存在");
        }
        // 密码校验
        String s = (MD5Util.md5(user.getPassword() + users.getSalt()));
        if (!users.getPassword().equals(s)) {
            throw new MyException(StateEnums.NAME_PASS_ERROR, "密码不正确");
        }
        queryWrapper.in(user.getPassword() != null, "passwordd", s);

//        String token = TokenUtil.sign(new User(user.getUsername(), s));
        String token = TokenUtil.sign(user);
        HashMap<String, Object> hs = new HashMap<>();
        hs.put("token", token);
        hs.put("uid", users.getUid());
        hs.put("nickname",users.getNickname());
        hs.put("avatar",users.getAvatar());
        hs.put("wxid",users.getWxid());
        hs.put("wxusername",user.getWxUsername());
        return new JsonResult<>(Integer.parseInt(String.valueOf(HttpStatus.OK.value())), "登录成功", hs);

    }

    //用户注册
    @PostMapping("register")
    public JsonResult<?> register(@Valid User user, BindingResult br) {
        if (br.hasErrors()) {
            throw new MyException(StateEnums.CHECK_ERROR,
                    Objects.requireNonNull(br.getFieldError()).getDefaultMessage());
        } else {
                // 查询用户名是否存在
                QueryWrapper<User> queryWrapper = new QueryWrapper<>();
                queryWrapper.eq("username", user.getUsername());
                User users = userService.getOne(queryWrapper);
                if (users != null) {
                    System.out.println(users);
                    throw new MyException(StateEnums.USER_ALREADY_EXISTS, "用户已存在");

                }
                // 设置盐
                String salt = UUIDUtil.getUUID();
                user.setSalt(salt);
                // 设置密码加密
                String s = MD5Util.md5(user.getPassword() + salt);
                user.setPassword(s);
                // 设置用户默认头像
                user.setAvatar(User.DEFAULT_AVATAR);
                if(user.getNickname()==null)
                    user.setNickname("用户"+user.getUsername()+System.currentTimeMillis());
                userService.save(user);
                return new JsonResult<>(StateEnums.SUCCESS.getCode(),"用户注册成功");
            }

    }
    //管理员新增用户
    @PostMapping("")
    public JsonResult<?> addUser(@Valid User user, BindingResult br) {
        if (br.hasErrors()) {
            throw new MyException(StateEnums.CHECK_ERROR,
                    Objects.requireNonNull(br.getFieldError()).getDefaultMessage());
        } else {
            // 查询用户名是否存在
            QueryWrapper<User> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("username", user.getUsername());
            User users = userService.getOne(queryWrapper);
            if (users != null) {
                System.out.println(users);
                throw new MyException(StateEnums.USER_ALREADY_EXISTS, "用户已存在");

            }
            // 设置盐
            String salt = UUIDUtil.getUUID();
            user.setSalt(salt);
            // 设置密码加密
            String s = MD5Util.md5(user.getPassword() + salt);
            user.setPassword(s);
            // 设置用户默认头像
            user.setAvatar(User.DEFAULT_AVATAR);
            if(user.getNickname()==null)
                user.setNickname("用户"+user.getUsername()+System.currentTimeMillis());
            userService.save(user);
            return new JsonResult<>(StateEnums.SUCCESS.getCode(),"用户注册成功");
        }

    }
    //修改密码,需要用户正确填写原密码
    @PutMapping("updatePassword")
    public JsonResult<?> updatePassword(@Valid User user){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("uid", user.getUid());
        User users = userService.getOne(queryWrapper);
        // 通过用户名从数据库中查询出该用户
        if (users == null) {
            throw new MyException(StateEnums.USER_DOES_NOT_EXISTS, "用户不存在");
        }
        // 密码校验
        String s = (MD5Util.md5(user.getOldPassword() + users.getSalt()));
        if (!users.getPassword().equals(s)) {
            throw new MyException(StateEnums.NAME_PASS_ERROR, "原密码不正确");
        }else {
            // 设置盐
            String salt = UUIDUtil.getUUID();
            users.setSalt(salt);
            // 设置密码加密
            String ss = MD5Util.md5(user.getPassword() + salt);
            users.setPassword(ss);
            userService.updateById(users);
            return new JsonResult<>(StateEnums.SUCCESS.getCode(),"密码修改成功");
        }
    }

    //忘记密码
    @PutMapping("forgetPassword")
    public  JsonResult<?> forgePassword(@Valid User user){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", user.getUsername());
        User users = userService.getOne(queryWrapper);
        if (users == null) {
            throw new MyException(StateEnums.USER_DOES_NOT_EXISTS, "用户不存在");
        }else{
            if(user.getPhone().equals(users.getPhone())){
                // 设置盐
                String salt = UUIDUtil.getUUID();
                user.setSalt(salt);
                // 设置密码加密
                String s = MD5Util.md5(user.getPassword() + salt);
                users.setPassword(s);
                userService.updateById(users);
                return new JsonResult<>(StateEnums.SUCCESS.getCode(),"密码修改成功");
            }else{
                throw new MyException(StateEnums.USER_INFO_MISMATCH, "用户电话号码不一致");
            }
        }
    }


    //绑定wxid
    @PutMapping("bindingWxid/{uid}/{wxopenid}/{wxUsername}")
    public JsonResult<?> bindingWxid(@PathVariable int uid,@PathVariable String wxopenid,@PathVariable String wxUsername){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(wxopenid != null, "wxid", wxopenid);
        User user = userService.getOne(queryWrapper);
        if(user != null ){
            if(user.getUid() != uid){
                throw new MyException(StateEnums.WXID_ALREADY_BINDED_OTHER, "该微信id已绑定其他账号");
            }else {
                throw new MyException(StateEnums.WXID_ALREADY_BINDED_SELF, "该微信id已绑定本账号");
            }
        }else{
            QueryWrapper<User> queryWrapper2 = new QueryWrapper<>();
            queryWrapper2.eq(uid != 0, "uid", uid);
            user = userService.getOne(queryWrapper2);
            if(user.getWxid()!=null){
                throw new MyException(StateEnums.USER_ALREADY_BIND_WXID, "您已绑定其他微信id，请先解绑");
            }else{
                user.setWxid(wxopenid);
                user.setWxUsername(wxUsername);
                boolean ifUpdate = userService.updateById(user);
                if (ifUpdate) {
                    return new JsonResult<>(StateEnums.SUCCESS.getCode(),"微信id绑定成功");
                } else {
                    throw new MyException(StateEnums.FAIL, "微信id绑定失败");
                }
            }
        }
    }
    //解绑微信id
    @PostMapping("unbindingWxid/{uid}")
    public JsonResult<?> unbindingWxid(@PathVariable int uid){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(uid != 0, "uid", uid);
        User user = userService.getOne(queryWrapper);
        if(user.getWxid()==null ){//|| user.getWxid().equals("")
            throw new MyException(StateEnums.USER_HAVE_NO_WXID, "您未绑定微信id");
        }else{
//            user.setWxid("");
            //强制null更新
            LambdaUpdateWrapper<User> wrapper = Wrappers.<User>lambdaUpdate()
                    .eq(User::getUid, uid)
                    .set(User::getWxid, null)
                    .set(User::getWxUsername, null);
            boolean ifUpdate = userService.update(wrapper);
            if (ifUpdate) {
                return new JsonResult<>(StateEnums.SUCCESS.getCode(),"成功解绑微信id");
            } else {
                throw new MyException(StateEnums.FAIL.getCode(), "解绑微信id失败");
            }
        }
    }
    //微信小程序端头像更新
    @PostMapping("upAvatar/{uid}")
    public JsonResult<?> upAvatar(@PathVariable int uid, String avatarUrl){
        User user = userService.getById(uid);
        if(user == null){
            throw new MyException(StateEnums.USER_DOES_NOT_EXISTS,"用户不存在");
        }
        user.setAvatar(avatarUrl);
        boolean isUp = userService.updateById(user);
        if(isUp){
            return new JsonResult<>(StateEnums.SUCCESS,"头像更新成功");
        }else{
            throw new MyException(StateEnums.UNKNOWN_ERROR,"头像上传失败");
        }
    }

    //更改用户状态
    @PutMapping("{uid}")
    public JsonResult<?> updateUserStatus(@PathVariable int uid, BindingResult br) {
        if (br.hasErrors()) {
            throw new MyException(StateEnums.CHECK_ERROR,
                    Objects.requireNonNull(br.getFieldError()).getDefaultMessage());
        } else {
            User u = userService.getById(uid);
            if (u.getStatus() == 1) {
                u.setStatus(0);
            } else {
                u.setStatus(1);
            }
            boolean isUpdate = userService.updateById(u);
            if (isUpdate) {
                return new JsonResult<>(StateEnums.SUCCESS.getCode(),"修改状态成功");
            } else {
                return new JsonResult<>(StateEnums.FAIL.getCode(), "修改状态失败");
            }
        }
    }

    /**
     * 修改用户头像，只是单纯增删改查的一起生成罢了，当初给小组成员练手写的方法，实际上不是这样更新的
     * @param uid 用户id
     * @param avatar 头像地址
     * @param br 报错提示
     * @return JsonResult
     */
    @Deprecated
    @PutMapping("avatar/{uid}")
    public JsonResult<?> updateUserAvatar(@PathVariable int uid, String avatar, BindingResult br) {
        if (br.hasErrors()) {
            throw new MyException(StateEnums.CHECK_ERROR,
                    Objects.requireNonNull(br.getFieldError()).getDefaultMessage());
        } else {
            User ua = userService.getById(uid);
            ua.setAvatar(avatar);
            boolean isUpdate = userService.updateById(ua);
            if (isUpdate) {
                return new JsonResult<>(StateEnums.SUCCESS.getCode(),"修改头像成功");
            } else {
                return new JsonResult<>(StateEnums.FAIL.getCode(), "修改头像失败");
            }
        }
    }

    /**
     * 废弃方法，删除用户逻辑不可能这么简单
     * @param uid 用户id
     * @param br 报错信息
     * @return JsonResult
     */
    //删除用户
    @Deprecated
    @DeleteMapping("{uid}")
    public JsonResult<?> removeUserByUid(@PathVariable int uid, BindingResult br) {
        if (br.hasErrors()) {
            throw new MyException(StateEnums.CHECK_ERROR,
                    Objects.requireNonNull(br.getFieldError()).getDefaultMessage());
        } else {
            boolean removeByUid = userService.removeById(uid);
            if (removeByUid) {
                return new JsonResult<>(StateEnums.SUCCESS.getCode(),"删除成功");
            } else {
                return new JsonResult<>(StateEnums.FAIL.getCode(), "删除失败");
            }
        }
    }

    //通过ID查找用户
    @GetMapping("{uid}")
    public JsonResult<?> getOneByUid(@PathVariable int uid) {
        return new JsonResult<>(userService.getById(uid));
    }
    //通过ID查找用户，但是不传密码
    @GetMapping("userdto/{uid}")
    public JsonResult<?> getUserByUid(@PathVariable int uid) {
        User user = userService.getById(uid);
        if(user == null){
            throw new MyException(StateEnums.USER_DOES_NOT_EXISTS,"用户不存在");
        }
        UserDTO returnUser = new UserDTO(user.getUid(),user.getUsername(),
                user.getAvatar(),user.getNickname(),user.getPhone(),user.getWxid());
        return new JsonResult<>(returnUser);
    }


    //查询所有数据
    @GetMapping("")
    public JsonResult<User> SelectAllUser() {
        List<User> records = userService.list();
        long total = records.size();
        return new JsonResult<>(total, records);
    }

    private final QiniuUtils qiniuUtils ;

    /**
     * 用户头像上传
     * @param file 上传的头像文件
     * @return JsonResult
     */
    @PostMapping("uploadUserAvatarToQiNiu")
    public JsonResult<?> uploadUserAvatarToQiNiu(@RequestParam MultipartFile file){
        //原始文件名称比如 aa.png
        String originalFilename = file.getOriginalFilename() ;
        //将原始名称修改为：唯一文件名称
        String fileName = UUID.randomUUID() + "." + StringUtils.substringAfterLast(originalFilename, ".");
        //上传文件，上传到哪呢？图片服务器七牛云
        //把图片发放到距离图片最近的服务器上，降低我们自身服务器的带宽消耗
        boolean upload = qiniuUtils.upload(file, fileName);
        if (upload){
            //上传成功
            String avatarUrl = QiniuUtils.url+fileName;
            logger.info("====upAvatar:"+avatarUrl+"====") ;

            //上传并不非要更新
            //TODO 如果不更新要把服务器上图片删掉，可是现在没空写了
//            User user = userService.getById(uid);
//            user.setAvatar(avatarUrl);
//            userService.updateById(user);
            return new JsonResult<>(StateEnums.SUCCESS.getCode(),"上传成功",avatarUrl);
        }

        return new JsonResult<>(StateEnums.UNKNOWN_ERROR.getCode(),"上传失败");
    }

    @PostMapping("/uploadUserAvatar/{uid}")
    public JsonResult<?> uploadUserAvatar(@RequestParam MultipartFile file,@PathVariable int uid,
                                          HttpServletRequest request) {
        /* 对字节数组字符串进行Base64解码并生成图片
        String strImge = "";
        GenerateImage(strImge, "D:\\11.jpg");*/
        //获取上传图片的原名称
        String fileName=file.getOriginalFilename();
        System.out.println(fileName);
        //获取要保存的目录
        String path =request.getServletContext().getRealPath("static/picture/avatar");
        //String myStubbenPath ="D:/Program/IdeaProjects/TicketCat/static/picture/avatar";
        String serverAvatarPath = "/usr/local/java/tpm/static/picture/avatar";
        System.out.println(path);
        //上传的文件最好是要重命名，这样才能保存不会因为上传相同名称的文件而报错
        fileName=System.currentTimeMillis()+"_"+fileName;
        System.out.println(fileName);
        //构造file对象
//        File myFile=new File(path+"/"+fileName);
        File myStubbenFile=new File(serverAvatarPath+"/"+fileName);
        logger.info("====AvatarFile:"+myStubbenFile+"====");
        try {
            //将前段的文件复制到后端服务器中
            FileUtils.copyInputStreamToFile(file.getInputStream(),myStubbenFile);
            System.out.println("上传成功");
            //上传成功后还需要更新数据库中的用户logo
            User user= userService.getById(uid);
            user.setAvatar(fileName);
            boolean flag2=userService.updateById(user);
            if(flag2){
//                user.setUser_logo(fileName);
                System.out.println("更新头像成功！");
                return new JsonResult<>(StateEnums.SUCCESS.getCode(),"上传成功");
            }
        }catch (Exception e){
            throw new MyException(StateEnums.UNKNOWN_ERROR, "上传失败");
        }

        return new JsonResult<>(StateEnums.UNKNOWN_ERROR.getCode(),"上传失败");
    }

    // 对字节数组字符串进行Base64解码并生成图片
//    public static boolean GenerateImage(String imgStr, String imgFilePath) {
//        if (imgStr == null) // 图像数据为空
//            return false;
//        BASE64Decoder decoder = new BASE64Decoder();
//        try {
//            // Base64解码
//            byte[] bytes = decoder.decodeBuffer(imgStr);
//            for (int i = 0; i < bytes.length; ++i) {
//                if (bytes[i] < 0) {// 调整异常数据
//                    bytes[i] += 256;
//                }
//            }
//            // 生成jpeg图片
//            OutputStream out = Files.newOutputStream(Paths.get(imgFilePath));
//            out.write(bytes);
//            out.flush();
//            out.close();
//            return true;
//        } catch (Exception e) {
//            return false;
//        }
//    }

    //分页查询
    @GetMapping("{current}/{limit}")
    public JsonResult<User> page(@PathVariable long current,@PathVariable long limit){
        Page<User> objectPage = new Page<>(current,limit);
        userService.page(objectPage,null);
        long total = objectPage.getTotal();
        List<User> records = objectPage.getRecords();
        return new JsonResult<>(total,records);
    }
}
