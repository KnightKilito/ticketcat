package com.tpm.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tpm.entity.Cinema;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
public interface CinemaMapper extends BaseMapper<Cinema> {
}
