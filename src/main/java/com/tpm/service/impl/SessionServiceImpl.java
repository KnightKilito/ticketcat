package com.tpm.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tpm.entity.Session;
import com.tpm.mapper.SessionMapper;
import com.tpm.service.SessionService;
import org.springframework.stereotype.Service;

@Service
public class SessionServiceImpl extends ServiceImpl<SessionMapper,Session> implements SessionService {



}
