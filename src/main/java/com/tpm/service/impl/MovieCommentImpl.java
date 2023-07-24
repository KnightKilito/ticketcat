package com.tpm.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tpm.entity.MovieComment;
import com.tpm.mapper.MovieCommentMapper;
import com.tpm.service.MovieCommentService;
import com.tpm.service.MovieService;
import org.springframework.stereotype.Service;

@Service
public class MovieCommentImpl extends ServiceImpl<MovieCommentMapper, MovieComment> implements MovieCommentService {
}
