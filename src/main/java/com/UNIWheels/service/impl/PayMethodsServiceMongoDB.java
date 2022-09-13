package com.UNIWheels.service.impl;

import com.UNIWheels.entities.PayMethods;
import com.UNIWheels.repository.PayMethodsRepository;
import com.UNIWheels.service.PayMethodsService;

import java.util.List;

public class PayMethodsServiceMongoDB implements PayMethodsService {

    private final PayMethodsRepository paymethodsRepository;

    public PayMethodsServiceMongoDB(PayMethodsRepository paymethodsRepository) {
        this.paymethodsRepository = paymethodsRepository;
    }


    @Override
    public PayMethods create(PayMethods user) {
        return null;
    }

    @Override
    public PayMethods findById(String id) {
        return null;
    }

    @Override
    public List<PayMethods> getAll() {
        return null;
    }

    @Override
    public void deleteById(String id) {

    }

    @Override
    public PayMethods update(PayMethods user, String userId) {
        return null;
    }
}
