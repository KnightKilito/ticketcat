package com.tpm.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tpm.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {

}
