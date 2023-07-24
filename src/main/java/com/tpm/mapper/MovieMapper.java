package com.tpm.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tpm.entity.Movie;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MovieMapper extends BaseMapper<Movie> {
}
