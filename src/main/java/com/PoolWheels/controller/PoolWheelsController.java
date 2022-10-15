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
    private final CommentService commentService;

    ModelMapper modelMapper = new ModelMapper();


    /**
     * It is the User class constructor
     *
     * @param payMethodService The payment methods service
     * @param tripService The trips service
     */
        public PoolWheelsController (PayMethodService payMethodService, TripService tripService, CommentService commentService) {
                this.payMethodService = payMethodService;
                this.tripService = tripService;
                this.commentService = commentService;
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
