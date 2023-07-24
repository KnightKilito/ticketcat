package com.tpm.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tpm.dto.UserDTO;
import com.tpm.entity.JsonResult;
import com.tpm.entity.User;
import com.tpm.exception.MyException;
import com.tpm.exception.enums.StateEnums;
import com.tpm.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/tokenUser")
public class TokenUserController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    UserService userService;

    //修改用户信息
    @PutMapping("")
    public JsonResult updateUserInfo(@Valid UserDTO userdto, BindingResult br) {
        if (br.hasErrors()) {
            throw new MyException(StateEnums.CHECK_ERROR, br.getFieldError().getDefaultMessage());
        } else {
            User user = userService.getById(userdto.getUid());
            //如果不相等说明要更新用户名，则需要判断用户名不能重复
            if(!user.getUsername().equals(userdto.getUsername())){
                QueryWrapper<User> queryWrapper = new QueryWrapper<>();
                queryWrapper.eq(userdto.getUsername() != null, "username", userdto.getUsername());
                User usernameUser = userService.getOne(queryWrapper);
                if(usernameUser != null){
                    throw new MyException(StateEnums.USER_ALREADY_EXISTS,"该用户名已存在");
                }
            }
            user.setAvatar(userdto.getAvatar());
            if(userdto.getAvatar().equals("")){
                user.setAvatar(null);
            }
            user.setUsername(userdto.getUsername());

            user.setPhone(userdto.getPhone());
            user.setNickname(userdto.getNickname());
            boolean ifUpdate = userService.updateById(user);
            if (ifUpdate) {
                return new JsonResult(StateEnums.SUCCESS.getCode(),"修改成功");
            } else {
                throw new MyException(StateEnums.FAIL.getCode(), "修改失败");
            }
        }
    }
}
