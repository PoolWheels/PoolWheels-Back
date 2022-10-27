package com.PoolWheels.service.impl;

import com.PoolWheels.entities.Comment;
import com.PoolWheels.repository.CommentRepository;
import com.PoolWheels.service.CommentService;
import com.mongodb.DuplicateKeyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * > This class is a service that implements the CommentService interface
 */
@Service
public class CommentServiceMongoDB implements CommentService {

    private final CommentRepository commentRepository;

    // A constructor that is being used to create a new instance of the class.
    public CommentServiceMongoDB(@Autowired CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    /**
     * It creates a comment and returns it.
     *
     * @param comment The comment object to be created.
     * @return The comment that was created.
     */
    @Override
    public Comment create(Comment comment) {
        try {
            commentRepository.insert(comment);
            return commentRepository.findById(comment.getId()).orElse(null);
        }catch(DuplicateKeyException e){
            System.out.println("The specified id is already registered");
            return null;
        }
    }

    /**
     * It returns the comment with the given id.
     *
     * @param id The id of the comment to be found.
     * @return The comment with the given id.
     */
    @Override
    public Comment findById(String id) {
        return commentRepository.findById(id).orElse(null);
    }

    /**
     * It returns a list of all comments.
     *
     * @return A list of all comments in the database.
     */
    @Override
    public List<Comment> getAll() {
        return commentRepository.findAll();
    }

    /**
     * It deletes a comment by its id.
     *
     * @param id The id of the comment to be deleted.
     */
    @Override
    public boolean deleteById(String id) {
        try {
            commentRepository.deleteById(id);
            return true;
        }catch (IllegalArgumentException e){
            return false;
        }
    }

    /**
     * If the comment exists, delete it and insert the new comment
     *
     * @param comment The comment object that you want to update.
     * @param id The id of the comment you want to update.
     * @return The comment is being returned.
     */
    @Override
    public Comment update(Comment comment, String id) {
        if (commentRepository.existsById(id)){
            commentRepository.deleteById(id);
            commentRepository.insert(comment);
        }
        return comment;
    }
}
