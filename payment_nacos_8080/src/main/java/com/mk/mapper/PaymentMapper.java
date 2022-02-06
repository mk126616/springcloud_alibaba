package com.mk.mapper;

import com.mk.entity.PaymentEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentMapper
{
    int createPayment(@Param("entity") PaymentEntity entity);

    List<PaymentEntity> getAllPayment();
}
