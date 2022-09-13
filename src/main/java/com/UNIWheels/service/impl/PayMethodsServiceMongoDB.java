package com.UNIWheels.service.impl;

import com.UNIWheels.entities.PayMethods;
import com.UNIWheels.repository.PayMethodsRepository;
import com.UNIWheels.service.PayMethodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class PayMethodsServiceMongoDB implements PayMethodsService {

    private final PayMethodsRepository paymethodsRepository;

    public PayMethodsServiceMongoDB(@Autowired PayMethodsRepository paymethodsRepository) {
        this.paymethodsRepository = paymethodsRepository;
    }


    @Override
    public PayMethods create(PayMethods paymethod) {
        paymethodsRepository.save(paymethod);
        return paymethod;
    }

    @Override
    public PayMethods findById(String id) {
        return paymethodsRepository.findById(id).get();
    }

    @Override
    public List<PayMethods> getAll() {
        return paymethodsRepository.findAll();
    }

    @Override
    public void deleteById(String id) {
        paymethodsRepository.deleteById(id);
    }

    @Override
    public PayMethods update(PayMethods paymethod, String userId) {
        paymethodsRepository.save(paymethod);
        return paymethodsRepository.findById(userId).get();
    }
}
