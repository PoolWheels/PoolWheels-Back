package com.UNIWheels.service.impl;

// Importing the necessary classes for the service to work.
import com.UNIWheels.entities.PayMethods;
import com.UNIWheels.repository.PayMethodsRepository;
import com.UNIWheels.service.PayMethodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * > This class is a service that implements the PayMethodsService interface
 */
@Service
public class PayMethodsServiceMongoDB implements PayMethodsService {

    // Creating a variable that is private and final.
    private final PayMethodsRepository paymethodsRepository;

    // This is a constructor that is used to create a new instance of the class.
    public PayMethodsServiceMongoDB(@Autowired PayMethodsRepository paymethodsRepository) {
        this.paymethodsRepository = paymethodsRepository;
    }


    /**
     * It creates a new paymethod.
     *
     * @param paymethod The object that will be created.
     * @return The paymethod object is being returned.
     */
    @Override
    public PayMethods create(PayMethods paymethod) {
        paymethodsRepository.save(paymethod);
        return paymethod;
    }

    /**
     * It returns the paymethodsRepository.findById(id).get();
     *
     * @param id The id of the paymethods you want to find.
     * @return The paymethodsRepository.findById(id).get() is returning the PayMethods object.
     */
    @Override
    public PayMethods findById(String id) {
        return paymethodsRepository.findById(id).get();
    }

    /**
     * It returns a list of all the paymethods in the database.
     *
     * @return A list of all the paymethods in the database.
     */
    @Override
    public List<PayMethods> getAll() {
        return paymethodsRepository.findAll();
    }

    /**
     * It deletes a paymethod by its id.
     *
     * @param id The id of the paymethods to delete.
     */
    @Override
    public void deleteById(String id) {
        paymethodsRepository.deleteById(id);
    }

    /**
     * It updates the paymethods table with the new information.
     *
     * @param paymethod The object that you want to update.
     * @param userId The userId of the user you want to update.
     * @return The updated paymethods object.
     */
    @Override
    public PayMethods update(PayMethods paymethod, String userId) {
        paymethodsRepository.save(paymethod);
        return paymethodsRepository.findById(userId).get();
    }
}
