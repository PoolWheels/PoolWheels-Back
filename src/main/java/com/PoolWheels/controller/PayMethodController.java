package com.PoolWheels.controller;


import com.PoolWheels.dto.PayMethodDto;
import com.PoolWheels.entities.PayMethod;
import com.PoolWheels.service.PayMethodService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping( "/api/v1/paymethod" )
public class PayMethodController {

    @Autowired
    private final PayMethodService payMethodService;

    ModelMapper modelMapper = new ModelMapper();

    /**
     * It is the PayMethod Controller class constructor
     *
     * @param payMethodService The Users service
     */
    public PayMethodController(PayMethodService payMethodService) {
        this.payMethodService = payMethodService;
    }

    /* Payment methods services */

    /**
     * > This function returns a list of all the pay methods
     *
     * @return A list of all the pay methods
     */
    @GetMapping
    public ResponseEntity<List<PayMethodDto>> getAllPayMethods () {
        try {
            List<PayMethod> payMethodList = payMethodService.getAll();
            ArrayList<PayMethodDto> data = new ArrayList<PayMethodDto>();
            if (!payMethodList.isEmpty()) {
                for (PayMethod pm : payMethodList) {
                    data.add(modelMapper.map(pm, PayMethodDto.class));
                }
            }
            return new ResponseEntity<List<PayMethodDto>> (data, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<List<PayMethodDto>> (HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * It returns a PayMethodDto object if the id is found in the database, otherwise it returns a 404 error
     *
     * @param id The id of the pay method you want to get.
     * @return A PayMethodDto object
     */
    @GetMapping("/{id}")
    public ResponseEntity<PayMethodDto> getPayMethodById(@PathVariable String id) {
        try {
            PayMethod payMethodTemp = payMethodService.findById(id);
            if (payMethodTemp != null) {
                return new ResponseEntity<PayMethodDto>(modelMapper.map(payMethodTemp, PayMethodDto.class), HttpStatus.OK);
            } else {
                return new ResponseEntity<PayMethodDto>(HttpStatus.NOT_FOUND);
            }
        } catch (NoSuchElementException nsee) {
            System.out.println("\nDoesn´t exist a payment method with the specified id");
            return new ResponseEntity<PayMethodDto>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<PayMethodDto>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * It creates a new pay method for a user.
     *
     * @param newPayMethodDto The object that will be created.
     * @return A ResponseEntity object is being returned.
     */
    @PostMapping
    public ResponseEntity<PayMethodDto> createNewUserPayMethod (@RequestBody PayMethodDto newPayMethodDto) {
        try {
            PayMethod payMethodTemp = payMethodService.create(modelMapper.map(newPayMethodDto, PayMethod.class));
            if (payMethodTemp != null) {
                return new ResponseEntity<PayMethodDto>(modelMapper.map(payMethodTemp, PayMethodDto.class), HttpStatus.CREATED);
            } else {
                return new ResponseEntity<PayMethodDto>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<PayMethodDto>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * It updates the pay method of a user.
     *
     * @param payMethodDto The object that will be updated.
     * @param id The id of the pay method to be updated.
     * @return A ResponseEntity<PayMethodDto> is being returned.
     */
    @PutMapping("/{id}")
    public ResponseEntity<PayMethodDto> updateUserPayMethod (@RequestBody PayMethodDto payMethodDto, @PathVariable String id) {
        try {
            PayMethod payMethodTemp = payMethodService.update(modelMapper.map(payMethodDto, PayMethod.class), id);
            if (payMethodTemp != null) {
                return new ResponseEntity<PayMethodDto>(modelMapper.map(payMethodTemp, PayMethodDto.class), HttpStatus.OK);
            } else {
                return new ResponseEntity<PayMethodDto>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<PayMethodDto>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * It deletes a pay method by id.
     *
     * @param id The id of the payMethod to be deleted.
     * @return A boolean value.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deletePayMethod (@PathVariable String id) {
        try {
            boolean deleted = payMethodService.deleteById(id);
            if (deleted) {
                return new ResponseEntity<Boolean>(true, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<Boolean>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * This function returns a list of payment methods associated with a user
     *
     * @param idUser The id of the user who is making the request.
     * @return A list of PayMethodDto objects
     */
    @GetMapping("/Paymeths/{idUser}")
    public ResponseEntity<List<PayMethodDto>> getPaymethodsByUser(@PathVariable String idUser){
        try{
            List<PayMethod> tripList = payMethodService.getTripsByIdUserTraveler(idUser);
            ArrayList<PayMethodDto> data = new ArrayList<PayMethodDto>();
            if (!tripList.isEmpty()) {
                for (PayMethod t : tripList) {
                    data.add(modelMapper.map(t, PayMethodDto.class));
                }
            }
            return new ResponseEntity<List<PayMethodDto>> (data, HttpStatus.OK);
        }catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity<List<PayMethodDto>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
