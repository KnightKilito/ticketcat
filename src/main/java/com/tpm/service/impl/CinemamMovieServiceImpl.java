package com.tpm.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tpm.entity.Cinema_Movie;
import com.tpm.mapper.CinemaMovieMapper;
import com.tpm.service.CinemaMovieService;
import org.springframework.stereotype.Service;

@Service
public class CinemamMovieServiceImpl extends ServiceImpl<CinemaMovieMapper, Cinema_Movie> implements CinemaMovieService {
}
