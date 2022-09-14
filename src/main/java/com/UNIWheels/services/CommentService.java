package com.UNIWheels.services;

import com.UNIWheels.entities.Comment;

import java.util.List;

public interface CommentService {
    Comment create(Comment comment);

    Comment findById( String id );

    List<Comment> getAll();

    void deleteById( String id );

    Comment update( Comment comment, String userId );
}
