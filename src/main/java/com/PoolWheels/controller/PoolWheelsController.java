package com.PoolWheels.controller;

import com.PoolWheels.dto.*;
import com.PoolWheels.entities.*;
import com.PoolWheels.service.CommentService;
import com.PoolWheels.service.PayMethodService;
import com.PoolWheels.service.TripService;
import com.PoolWheels.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;


@RestController
public class PoolWheelsController {

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
        public PoolWheelsController (PayMethodService payMethodService, TripService tripService, UserService userService, CommentService commentService) {
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
                } catch (NoSuchElementException nsee) {
                System.out.println("\nDoesn´t exist a trip with the specified id");
                return new ResponseEntity<TripDto>(HttpStatus.NOT_FOUND);
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

        /**
         * The function receives the id of the trip and the id of the passenger, and returns a boolean value indicating whether
         * the reservation was successful or not
         *
         * @param idTrip The id of the trip that the passenger wants to reserve.
         * @param idPassenger The passenger's ID.
         * @return A boolean value.
         */
        @PostMapping("/api/v1/trips/{idTrip}/passengers/{idPassenger}")
        public ResponseEntity<Boolean> doReservartion (@PathVariable String idTrip, @PathVariable String idPassenger) {
                try {
                boolean reserved = tripService.reservation(idTrip, idPassenger);
                if (reserved) {
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
         * It removes a passenger from a trip.
         *
         * @param idTrip the id of the trip
         * @param idPassenger The id of the passenger to be removed from the trip.
         * @return A boolean value.
         */
        @DeleteMapping("/api/v1/trips/{idTrip}/passengers/{idPassenger}")
        public ResponseEntity<Boolean> removePassengerReservation (@PathVariable String idTrip, @PathVariable String idPassenger) {
                try {
                boolean removed = tripService.removeReservation(idTrip, idPassenger);
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


        /* Comments services */

        /**
         * It gets all the comments from the database and returns them as a list of CommentDto objects
         *
         * @return A list of comments
         */
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

        /**
         * It returns a CommentDto object if the comment exists, or a 404 if it doesn't, or a 500 if something goes wrong
         *
         * @param id The id of the comment to be retrieved.
         * @return A comment with the given id.
         */
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

        /**
         * It takes a CommentDto object as a parameter, converts it to a Comment object, and then passes it to the create()
         * function in the CommentService class
         *
         * @param newCommentDto The new comment that we want to create.
         * @return A new comment is being returned.
         */
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

        /**
         * It updates a comment with the given id.
         *
         * @param commentDto The object that will be updated.
         * @param id The id of the comment to be updated.
         * @return A ResponseEntity object is being returned.
         */
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

        /**
         * It deletes a comment by id.
         *
         * @param id The id of the comment to be deleted
         * @return A boolean value is being returned.
         */
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
