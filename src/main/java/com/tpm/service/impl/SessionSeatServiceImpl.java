package com.tpm.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tpm.entity.SessionSeat;
import com.tpm.mapper.SessionSeatMapper;
import com.tpm.service.SessionSeatService;
import org.springframework.stereotype.Service;

@Service
public class SessionSeatServiceImpl extends ServiceImpl<SessionSeatMapper, SessionSeat> implements SessionSeatService{

}
