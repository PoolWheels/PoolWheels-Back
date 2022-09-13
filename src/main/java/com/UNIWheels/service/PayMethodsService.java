package com.UNIWheels.service;

// This is importing the PayMethods class and the List class.
import com.UNIWheels.entities.PayMethods;

import java.util.List;

// This is the interface for the PayMethodsService. It is a contract that says that any class that implements this
// interface must have these methods.
public interface PayMethodsService {

    PayMethods create(PayMethods user );

    PayMethods findById( String id );

    List<PayMethods> getAll();

    void deleteById( String id );

    PayMethods update(PayMethods user, String userId );

}
