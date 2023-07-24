package com.tpm.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tpm.entity.Ticket;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TicketMapper extends BaseMapper<Ticket> {
}
