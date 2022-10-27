package com.PoolWheels.service;

// This is importing the PayMethods class and the List class.
import com.PoolWheels.entities.PayMethod;
import java.util.List;

// This is the interface for the PayMethodsService. It is a contract that says that any class that implements this
// interface must have these methods

public interface PayMethodService {

    PayMethod create(PayMethod user );

    PayMethod findById(String id );

    List<PayMethod> getAll();

    boolean deleteById( String id );

    PayMethod update(PayMethod user, String userId );

    List<PayMethod> getTripsByIdUserTraveler(String idUser);

}
