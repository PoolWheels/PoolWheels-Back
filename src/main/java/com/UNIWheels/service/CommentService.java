package com.UNIWheels.service;

import com.UNIWheels.entities.Comment;

import java.util.List;

// A service interface that defines the methods that will be used in the service class.
public interface CommentService {
    /**
     * Create a new comment.
     *
     * @param comment The comment object to be created.
     * @return The comment that was created.
     */
    Comment create(Comment comment);

    /**
     * Find a comment by its id.
     *
     * @param id The id of the comment to find.
     * @return A Comment object.
     */
    Comment findById( String id );

    /**
     * Get all comments.
     *
     * @return A list of all the comments in the database.
     */
    List<Comment> getAll();

    /**
     * Delete a document by id
     *
     * @param id The id of the document to delete.
     */
    void deleteById( String id );

    /**
     * Update a comment in the database.
     *
     * @param comment The comment object to be updated.
     * @param userId The userId of the user who is updating the comment.
     * @return The updated comment.
     */
    Comment update( Comment comment, String userId );
}
