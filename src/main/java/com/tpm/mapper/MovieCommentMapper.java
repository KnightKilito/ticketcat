package com.tpm.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tpm.entity.MovieComment;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MovieCommentMapper extends BaseMapper<MovieComment> {
}
