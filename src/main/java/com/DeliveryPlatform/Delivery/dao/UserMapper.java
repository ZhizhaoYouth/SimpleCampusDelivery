package com.DeliveryPlatform.Delivery.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.DeliveryPlatform.Delivery.entity.User;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface UserMapper extends BaseMapper<User> {
}
