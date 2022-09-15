package com.UNIWheels.service.impl;

// Importing the necessary classes for the service to work.
import com.UNIWheels.entities.PayMethod;
import com.UNIWheels.repository.PayMethodRepository;
import com.UNIWheels.service.PayMethodService;
import com.mongodb.DuplicateKeyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


/**
 * > This class is a service that implements the PayMethodsService interface
 */
@Service
public class PayMethodServiceMongoDB implements PayMethodService {

    // Creating a variable that is private and final.
    private final PayMethodRepository paymethodsRepository;

    // This is a constructor that is used to create a new instance of the class.
    public PayMethodServiceMongoDB(@Autowired PayMethodRepository paymethodsRepository) {
        this.paymethodsRepository = paymethodsRepository;
    }


    /**
     * It creates a new paymethod.
     *
     * @param paymethod The object that will be created.
     * @return The paymethod object is being returned.
     */
    @Override
    public PayMethod create(PayMethod paymethod) {
        try {
            paymethodsRepository.insert(paymethod);
            Optional<PayMethod> userTemp = paymethodsRepository.findById(paymethod.getId());
            return userTemp.orElse(null);
        } catch (DuplicateKeyException e) {
            System.out.println("The specified id is already registered");
            return null;
        }
    }

    /**
     * It returns the paymethodsRepository.findById(id).get();
     *
     * @param id The id of the paymethods you want to find.
     * @return The paymethodsRepository.findById(id).get() is returning the PayMethods object.
     */
    @Override
    public PayMethod findById(String id) {
        return paymethodsRepository.findById(id).get();
    }

    /**
     * It returns a list of all the paymethods in the database.
     *
     * @return A list of all the paymethods in the database.
     */
    @Override
    public List<PayMethod> getAll() {
        return paymethodsRepository.findAll();
    }

    /**
     * It deletes a paymethod by its id.
     *
     * @param id The id of the paymethods to delete.
     * @return
     */
    @Override
    public boolean deleteById(String id) {
        try {
            paymethodsRepository.deleteById(id);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    /**
     * It updates the paymethods table with the new information.
     *
     * @param paymethod The object that you want to update.
     * @param userId The userId of the user you want to update.
     * @return The updated paymethods object.
     */
    @Override
    public PayMethod update(PayMethod paymethod, String userId) {
        if (paymethodsRepository.existsById(userId)) {
            return paymethodsRepository.save(paymethod);
        } else {
            System.out.println("The specified id is not registered");
            return null;
        }
    }
}
