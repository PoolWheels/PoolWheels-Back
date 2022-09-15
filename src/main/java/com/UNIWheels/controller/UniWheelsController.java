package com.UNIWheels.controller;

import org.apache.catalina.LifecycleState;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.UNIWheels.entities.PayMethod;


@RestController
public class UniWheelsController {

    @Autowired
    private final PayMethodService payMethodService;

    @Autowired
    private final TripService tripService;

    @Autowired
    private final UserService userService;

    ModelMapper modelMapper = new ModelMapper();


    /**
     * It is the User class constructor
     *
     * @param payMethodService The payment methods service
     * @param tripService The trips service
     * @param userService The Users service
     */
    public UniWheelsController (PayMethodService payMethodService, TripService tripService, UserService userService) {
        this.payMethodService = payMethodService;
        this.tripService = tripService;
        this.userService = userService;
    }

    /* Payment methods services */

    /**
     * > This function returns a list of all the pay methods
     *
     * @return A list of all the pay methods
     */
    @GetMapping("/api/v1/paymethots")
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
    @GetMapping("/api/v1/paymethots/{id}")
    public ResponseEntity<PayMethodDto> getPayMethodById(@PathVariable String id) {
        try {
            PayMethod payMethodTemp = payMethodService.findById(id);
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
     * It creates a new pay method for a user.
     *
     * @param newPayMethodDto The object that will be created.
     * @return A ResponseEntity object is being returned.
     */
    @PostMapping("/api/v1/paymethots")
    public ResponseEntity<PayMethodDto> createNewUserPayMethod (@RequestBody PayMethodDto newPayMethodDto) {
        try {
            PayMethod payMethodTemp = payMethodService.create(modelMapper.map(newPayMethodDto, PayMethod.class));
            if (payMethodTemp != null) {
                return new ResponseEntity<PayMethodDto>(modelMapper.map(payMethodTemp, PayMethodDto.class), HttpStatus.CREATED);
            } else {
                return new ResponseEntity<PayMethdDto>(HttpStatus.NOT_FOUND);
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
    @PutMapping("/api/v1/paymethots/{id}")
    public ResponseEntity<PayMethodDto> updateUserPayMethod (@RequestBody PayMethodDto payMethodDto, @PathVariable String id) {
        try {
            PayMethod payMethodTemp = payMethodService.update(modelMapper.map(payMethodDto, PayMethod.class), id);
            if (payMethodTemp != null) {
                return new ResponseEntity<PayMethodDto>(payMethodTemp, HttpStatus.OK);
            } else {
                return new ResponseEntity<PayMethdDto>(HttpStatus.NOT_FOUND);
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
    @DeleteMapping("/api/v1/paymethots/{id}")
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

    /* Trips services */

    /**
     * It gets all the trips from the database and returns them as a list of TripDto objects
     *
     * @return A list of TripDto objects.
     */
    @GetMapping("/api/v1/trips")
    public ResponseEntity<List<TripDto>> getAllTrips () {
        try {
            List<Trip> tripList = tripService.getAll();
            ArrayList<TripDto> data = new ArrayList<TripDto>();
            if (!tripList.isEmpty()) {
                for (Trip t : tripList) {
                    data.add(modelMapper.map(t, TripDto.class));
                }
            }
            return new ResponseEntity<List<TripDto>> (data, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<List<TripDto>> (HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * It returns a trip by id.
     *
     * @param id The id of the trip you want to retrieve.
     * @return A trip object
     */
    @GetMapping("/api/v1/trips/{id}")
    public ResponseEntity<TripDto> getTripById(@PathVariable String id) {
        try {
            Trip tripTemp = tripService.findById(id);
            if (tripTemp != null) {
                return new ResponseEntity<TripDto>(modelMapper.map(tripTemp, TripDto.class), HttpStatus.OK);
            } else {
                return new ResponseEntity<TripDto>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<TripDto>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * It creates a new trip.
     *
     * @param newTripDto The object that will be created.
     * @return The new trip created.
     */
    @PostMapping("/api/v1/trips")
    public ResponseEntity<TripDto> createNewTrip (@RequestBody TripDto newTripDto) {
        try {
            Trip tripTemp = tripService.create(modelMapper.map(newTripDto, Trip.class));
            if (tripTemp != null) {
                return new ResponseEntity<TripDto>(modelMapper.map(tripTemp, TripDto.class), HttpStatus.CREATED);
            } else {
                return new ResponseEntity<TripDto>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<TripDto>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * It updates a trip with the given id.
     *
     * @param tripDto The object that will be updated.
     * @param id The id of the trip to be updated.
     * @return A ResponseEntity object is being returned.
     */
    @PutMapping("/api/v1/trips/{id}")
    public ResponseEntity<TripDto> updateTrip (@RequestBody TripDto tripDto, @PathVariable String id) {
        try {
            Trip tripTemp = tripService.update(modelMapper.map(tripDto, Trip.class), id);
            if (tripTemp != null) {
                return new ResponseEntity<TripDto>(tripTemp, HttpStatus.OK);
            } else {
                return new ResponseEntity<TripDto>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<TripDto>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * It deletes a trip by id.
     *
     * @param id The id of the trip to be deleted
     * @return A boolean value is being returned.
     */
    @DeleteMapping("/api/v1/trips/{id}")
    public ResponseEntity<Boolean> deleteTrip (@PathVariable String id) {
        try {
            boolean deleted = tripService.deleteById(id);
            if (deleted) {
                return new ResponseEntity<Boolean>(true, HttpStatus.OK);
            } else {
                return new ResponseEntity<Boolean>(false, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<Boolean>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /* Driver users services */

    @GetMapping("/api/v1/driverusers")
    public ResponseEntity<List<UserDriverDto>> getAllDriverUsers () {
        try {
            List<UserDriver> userDriverList = userService.getAllDriver();
            ArrayList<UserDriverDto> data = new ArrayList<UserDriverDto>();
            if (!userDriverList.isEmpty()) {
                for (UserDriver ud : userDriverList) {
                    data.add(modelMapper.map(ud, UserDriverDto.class));
                }
            }
            return new ResponseEntity<List<UserDriverDto>> (data, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<List<UserDriverDto>> (HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/api/v1/driverusers/{id}")
    public ResponseEntity<UserDriverDto> getDriverUserById(@PathVariable String id) {
        try {
            Trip tripTemp = userService.findByIdDriver(id);
            if (tripTemp != null) {
                return new ResponseEntity<UserDriverDto>(modelMapper.map(tripTemp, UserDriverDto.class), HttpStatus.OK);
            } else {
                return new ResponseEntity<UserDriverDto>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<UserDriverDto>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/api/v1/driverusers")
    public ResponseEntity<UserDriverDto> createNewDriverUser (@RequestBody UserDriverDto newUserDriverDto) {
        try {
            UserDriver userDriverTemp = userService.createUserDriver(modelMapper.map(newUserDriverDto, UserDriver.class));
            if (userDriverTemp != null) {
                return new ResponseEntity<UserDriverDto>(modelMapper.map(userDriverTemp, UserDriverDto.class), HttpStatus.CREATED);
            } else {
                return new ResponseEntity<UserDriverDto>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<UserDriverDto>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/api/v1/driverusers/{id}")
    public ResponseEntity<UserDriverDto> updateDriverUser (@RequestBody UserDriverDto userDriverDto, @PathVariable String id) {
        try {
            UserDriver userDriverTemp = userService.updateDriver(modelMapper.map(userDriverDto, UserDriver.class), id);
            if (userDriverTemp != null) {
                return new ResponseEntity<UserDriverDto>(userDriverTemp, HttpStatus.OK);
            } else {
                return new ResponseEntity<UserDriverDto>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<UserDriverDto>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/api/v1/driverusers/{id}")
    public ResponseEntity<Boolean> deleteDriverUser (@PathVariable String id) {
        try {
            boolean deleted = userService.deleteDriver(id);
            if (deleted) {
                return new ResponseEntity<Boolean>(true, HttpStatus.OK);
            } else {
                return new ResponseEntity<Boolean>(false, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<Boolean>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /* Traveler users services */

    @GetMapping("/api/v1/travelerusers")
    public ResponseEntity<List<UserTravelerDto>> getAllTravelerUsers () {
        try {
            List<UserTraveler> userTravelerList = userService.getAllTraveler();
            ArrayList<UserTravelerDto> data = new ArrayList<UserTravelerDto>();
            if (!userTravelerList.isEmpty()) {
                for (UserTraveler ud : userTravelerList) {
                    data.add(modelMapper.map(ud, UserTravelerDto.class));
                }
            }
            return new ResponseEntity<List<UserTravelerDto>> (data, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<List<UserTravelerDto>> (HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/api/v1/travelerusers/{id}")
    public ResponseEntity<UserTravelerDto> getTravelerUserById(@PathVariable String id) {
        try {
            UserTraveler userTravelerTemp = userService.findByIdTraveler(id);
            if (userTravelerTemp != null) {
                return new ResponseEntity<UserTravelerDto>(modelMapper.map(userTravelerTemp, UserTravelerDto.class), HttpStatus.OK);
            } else {
                return new ResponseEntity<UserTravelerDto>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<UserTravelerDto>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/api/v1/travelerusers")
    public ResponseEntity<UserTravelerDto> createNewTravelerUser (@RequestBody UserTravelerDto newUserTravelerDto) {
        try {
            UserTraveler userTravelerTemp = userService.createUserTraveler(modelMapper.map(newUserTravelerDto, UserTraveler.class));
            if (userTravelerTemp != null) {
                return new ResponseEntity<UserTravelerDto>(modelMapper.map(userTravelerTemp, UserTravelerDto.class), HttpStatus.CREATED);
            } else {
                return new ResponseEntity<UserTravelerDto>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<UserTravelerDto>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/api/v1/travelerusers/{id}")
    public ResponseEntity<UserTravelerDto> updateTravelerUser (@RequestBody UserTravelerDto userTravelerDto, @PathVariable String id) {
        try {
            UserTraveler userTravelerTemp = userService.updateTraveler(modelMapper.map(UserTravelerDto, UserTraveler.class), id);
            if (userTravelerTemp != null) {
                return new ResponseEntity<UserTravelerDto>(userTravelerTemp, HttpStatus.OK);
            } else {
                return new ResponseEntity<UserTravelerDto>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<UserTravelerDto>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/api/v1/travelerusers/{id}")
    public ResponseEntity<Boolean> deleteTravelerUser (@PathVariable String id) {
        try {
            boolean deleted = userService.deleteTraveler(id);
            if (deleted) {
                return new ResponseEntity<Boolean>(true, HttpStatus.OK);
            } else {
                return new ResponseEntity<Boolean>(false, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<Boolean>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
