package com.DeliveryPlatform.Delivery.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.DeliveryPlatform.Delivery.entity.Payment;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PaymentMapper extends BaseMapper<Payment> {
}
