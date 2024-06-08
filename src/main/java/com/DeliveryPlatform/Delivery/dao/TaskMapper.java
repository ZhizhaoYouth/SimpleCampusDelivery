package com.DeliveryPlatform.Delivery.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.DeliveryPlatform.Delivery.entity.Task;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TaskMapper extends BaseMapper<Task> {
}
