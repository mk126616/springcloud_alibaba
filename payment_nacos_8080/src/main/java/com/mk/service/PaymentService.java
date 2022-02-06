package com.mk.service;

import com.mk.entity.PaymentEntity;
import com.mk.entity.Result;

public interface PaymentService
{
    Result createPayment(PaymentEntity entity);

    Result getAllPayment();
}
