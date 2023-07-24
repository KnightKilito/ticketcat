package com.tpm.service.impl;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tpm.entity.Cinema;
import com.tpm.mapper.CinemaMapper;
import com.tpm.service.CinemaService;
import org.springframework.stereotype.Service;

@Service
public class CinemaServiceImpl extends ServiceImpl<CinemaMapper, Cinema> implements CinemaService {
}
