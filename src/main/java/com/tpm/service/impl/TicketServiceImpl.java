package com.tpm.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tpm.entity.Ticket;
import com.tpm.mapper.TicketMapper;
import com.tpm.service.TicketService;
import org.springframework.stereotype.Service;

@Service
public class TicketServiceImpl extends ServiceImpl<TicketMapper, Ticket> implements TicketService {
}
