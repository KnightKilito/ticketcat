package com.tpm.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tpm.entity.Hall;
import com.tpm.mapper.HallMapper;
import com.tpm.service.HallService;
import org.springframework.stereotype.Service;

@Service
public class HallServiceImpl extends ServiceImpl<HallMapper, Hall> implements HallService {
}
