package com.DeliveryPlatform.Delivery.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Data
@TableName("task")
public class Task {
    @TableId(type = IdType.AUTO)
    private Long taskId;
    private String title;
    private String description;
    private Long publisherId;
    private Long acceptorId;
    private String status;
    private LocalDateTime createdAt;
}
