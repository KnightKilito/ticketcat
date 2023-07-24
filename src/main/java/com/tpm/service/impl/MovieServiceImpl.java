package com.tpm.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tpm.entity.Movie;
import com.tpm.mapper.MovieMapper;
import com.tpm.service.MovieService;
import org.springframework.stereotype.Service;

@Service
public class MovieServiceImpl extends ServiceImpl<MovieMapper, Movie> implements MovieService {
}
