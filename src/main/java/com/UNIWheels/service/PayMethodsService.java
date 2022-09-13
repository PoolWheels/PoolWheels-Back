package com.UNIWheels.service;

import com.UNIWheels.entities.PayMethods;

import java.util.List;

public interface PayMethodsService {

    PayMethods create(PayMethods user );

    PayMethods findById( String id );

    List<PayMethods> getAll();

    void deleteById( String id );

    PayMethods update(PayMethods user, String userId );

}
