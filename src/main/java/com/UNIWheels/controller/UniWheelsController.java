package com.UNIWheels.controller;

import com.UNIWheels.dto.*;
import com.UNIWheels.entities.*;
import com.UNIWheels.service.CommentService;
import com.UNIWheels.service.PayMethodService;
import com.UNIWheels.service.TripService;
import com.UNIWheels.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
public class UniWheelsController {

    @Autowired
    private final PayMethodService payMethodService;

    @Autowired
    private final TripService tripService;

    @Autowired
    private final UserService userService;

    @Autowired
    private final CommentService commentService;

    ModelMapper modelMapper = new ModelMapper();


    /**
     * It is the User class constructor
     *
     * @param payMethodService The payment methods service
     * @param tripService The trips service
     * @param userService The Users service
     */
    public UniWheelsController (PayMethodService payMethodService, TripService tripService, UserService userService, CommentService commentService) {
        this.payMethodService = payMethodService;
        this.tripService = tripService;
        this.userService = userService;
        this.commentService = commentService;
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
    @PutMapping("/api/v1/paymethots/{id}")
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

    /**
     * It returns a boolean value that indicates whether the trip with the given id has full quotas or not
     *
     * @param id The id of the trip
     * @return A boolean value.
     */
    @GetMapping("/api/v1/trips/{id}/availableseats")
    public ResponseEntity<Boolean> areFullQuotas (@PathVariable String id) {
        try {
            boolean fullQuotas = tripService.fullQuotas(id);
            if (fullQuotas) {
                return new ResponseEntity<Boolean>(true, HttpStatus.OK);
            } else {
                return new ResponseEntity<Boolean>(false, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<Boolean>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/api/v1/trips/{idTrip}/passengers/{idPassenger}")
    public ResponseEntity<Boolean> removePassengerReservation (@PathVariable String idTrip, @PathVariable String idPassenger) {
        try {
            //boolean removed = tripService.removeReservation(idTrip, idPassenger);
            boolean removed = true;
            if (removed) {
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

    /**
     * It gets all the driver users from the database and returns them as a list of UserDriverDTO objects
     *
     * @return A list of UserDriverDTO objects.
     */
    @GetMapping("/api/v1/driverusers")
    public ResponseEntity<List<UserDriverDTO>> getAllDriverUsers () {
        try {
            List<UserDriver> userDriverList = userService.getAllDriver();
            ArrayList<UserDriverDTO> data = new ArrayList<UserDriverDTO>();
            if (!userDriverList.isEmpty()) {
                for (UserDriver ud : userDriverList) {
                    data.add(modelMapper.map(ud, UserDriverDTO.class));
                }
            }
            return new ResponseEntity<List<UserDriverDTO>> (data, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<List<UserDriverDTO>> (HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * This function is used to get a driver user by id
     *
     * @param id The id of the driver user you want to get.
     * @return A UserDriverDTO object
     */
    @GetMapping("/api/v1/driverusers/{id}")
    public ResponseEntity<UserDriverDTO> getDriverUserById(@PathVariable String id) {
        try {
            UserDriver userDriverTemp = userService.findByIdDriver(id);
            if (userDriverTemp != null) {
                return new ResponseEntity<UserDriverDTO>(modelMapper.map(userDriverTemp, UserDriverDTO.class), HttpStatus.OK);
            } else {
                return new ResponseEntity<UserDriverDTO>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<UserDriverDTO>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * The function creates a new driver user and returns the newly created user driver object
     *
     * @param newUserDriverDTO This is the object that will be passed in the request body.
     * @return A ResponseEntity object is being returned.
     */
    @PostMapping("/api/v1/driverusers")
    public ResponseEntity<UserDriverDTO> createNewDriverUser (@RequestBody UserDriverDTO newUserDriverDTO) {
        try {
            UserDriver userDriverTemp = userService.createUserDriver(modelMapper.map(newUserDriverDTO, UserDriver.class));
            if (userDriverTemp != null) {
                return new ResponseEntity<UserDriverDTO>(modelMapper.map(userDriverTemp, UserDriverDTO.class), HttpStatus.CREATED);
            } else {
                return new ResponseEntity<UserDriverDTO>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<UserDriverDTO>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * This function updates a driver user in the database
     *
     * @param UserDriverDTO The object that will be sent to the server.
     * @param id The id of the user to be updated.
     * @return A response entity with a user driver DTO and a status code of OK.
     */
    @PutMapping("/api/v1/driverusers/{id}")
    public ResponseEntity<UserDriverDTO> updateDriverUser (@RequestBody UserDriverDTO UserDriverDTO, @PathVariable String id) {
        try {
            UserDriver userDriverTemp = userService.updateDriver(modelMapper.map(UserDriverDTO, UserDriver.class), id);
            if (userDriverTemp != null) {
                return new ResponseEntity<UserDriverDTO>(modelMapper.map(userDriverTemp, UserDriverDTO.class), HttpStatus.OK);
            } else {
                return new ResponseEntity<UserDriverDTO>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<UserDriverDTO>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * It deletes a driver user from the database.
     *
     * @param id The id of the driver user to be deleted.
     * @return A boolean value is being returned.
     */
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

    /**
     * This function is used to get all the traveler users from the database
     *
     * @return A list of all the traveler users in the database.
     */
    @GetMapping("/api/v1/travelerusers")
    public ResponseEntity<List<UserTravelerDTO>> getAllTravelerUsers () {
        try {
            List<UserTraveler> userTravelerList = userService.getAllTraveler();
            ArrayList<UserTravelerDTO> data = new ArrayList<UserTravelerDTO>();
            if (!userTravelerList.isEmpty()) {
                for (UserTraveler ud : userTravelerList) {
                    data.add(modelMapper.map(ud, UserTravelerDTO.class));
                }
            }
            return new ResponseEntity<List<UserTravelerDTO>> (data, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<List<UserTravelerDTO>> (HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * This function is used to get a traveler user by id
     *
     * @param id The id of the user to be retrieved.
     * @return A UserTravelerDTO object is being returned.
     */
    @GetMapping("/api/v1/travelerusers/{id}")
    public ResponseEntity<UserTravelerDTO> getTravelerUserById(@PathVariable String id) {
        try {
            UserTraveler userTravelerTemp = userService.findByIdTraveler(id);
            if (userTravelerTemp != null) {
                return new ResponseEntity<UserTravelerDTO>(modelMapper.map(userTravelerTemp, UserTravelerDTO.class), HttpStatus.OK);
            } else {
                return new ResponseEntity<UserTravelerDTO>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<UserTravelerDTO>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * This function creates a new traveler user and returns the newly created user's information in the response body
     *
     * @param newUserTravelerDTO This is the object that will be passed in the request body.
     * @return A new user traveler is being returned.
     */
    @PostMapping("/api/v1/travelerusers")
    public ResponseEntity<UserTravelerDTO> createNewTravelerUser (@RequestBody UserTravelerDTO newUserTravelerDTO) {
        try {
            UserTraveler userTravelerTemp = userService.createUserTraveler(modelMapper.map(newUserTravelerDTO, UserTraveler.class));
            if (userTravelerTemp != null) {
                return new ResponseEntity<UserTravelerDTO>(modelMapper.map(userTravelerTemp, UserTravelerDTO.class), HttpStatus.CREATED);
            } else {
                return new ResponseEntity<UserTravelerDTO>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<UserTravelerDTO>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * This function updates a traveler user in the database
     *
     * @param UserTravelerDTO The object that will be updated.
     * @param id The id of the user to be updated.
     * @return The method returns a ResponseEntity<UserTravelerDTO> object.
     */
    @PutMapping("/api/v1/travelerusers/{id}")
    public ResponseEntity<UserTravelerDTO> updateTravelerUser (@RequestBody UserTravelerDTO UserTravelerDTO, @PathVariable String id) {
        try {
            UserTraveler userTravelerTemp = userService.updateTraveler(modelMapper.map(UserTravelerDTO, UserTraveler.class), id);
            if (userTravelerTemp != null) {
                return new ResponseEntity<UserTravelerDTO>(modelMapper.map(userTravelerTemp, UserTravelerDTO.class), HttpStatus.OK);
            } else {
                return new ResponseEntity<UserTravelerDTO>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<UserTravelerDTO>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * This function deletes a traveler user from the database
     *
     * @param id The id of the traveler user to be deleted.
     * @return A boolean value is being returned.
     */
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

    /* Comments services */

    @GetMapping("/api/v1/comments")
    public ResponseEntity<List<CommentDto>> getAllComments () {
        try {
            List<Comment> commentList = commentService.getAll();
            ArrayList<CommentDto> data = new ArrayList<CommentDto>();
            if (!commentList.isEmpty()) {
                for (Comment c : commentList) {
                    data.add(modelMapper.map(c, CommentDto.class));
                }
            }
            return new ResponseEntity<List<CommentDto>> (data, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<List<CommentDto>> (HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/api/v1/comments/{id}")
    public ResponseEntity<CommentDto> getCommentById(@PathVariable String id) {
        try {
            Comment commentTemp = commentService.findById(id);
            if (commentTemp != null) {
                return new ResponseEntity<CommentDto>(modelMapper.map(commentTemp, CommentDto.class), HttpStatus.OK);
            } else {
                return new ResponseEntity<CommentDto>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<CommentDto>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/api/v1/comments")
    public ResponseEntity<CommentDto> createNewComment (@RequestBody CommentDto newCommentDto) {
        try {
            Comment commentTemp = commentService.create(modelMapper.map(newCommentDto, Comment.class));
            if (commentTemp != null) {
                return new ResponseEntity<CommentDto>(modelMapper.map(commentTemp, CommentDto.class), HttpStatus.CREATED);
            } else {
                return new ResponseEntity<CommentDto>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<CommentDto>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/api/v1/comments/{id}")
    public ResponseEntity<CommentDto> updateComment (@RequestBody CommentDto commentDto, @PathVariable String id) {
        try {
            Comment commentTemp = commentService.update(modelMapper.map(commentDto, Comment.class), id);
            if (commentTemp != null) {
                return new ResponseEntity<CommentDto>(modelMapper.map(commentTemp, CommentDto.class), HttpStatus.OK);
            } else {
                return new ResponseEntity<CommentDto>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<CommentDto>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/api/v1/comments/{id}")
    public ResponseEntity<Boolean> deleteComment (@PathVariable String id) {
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
}
