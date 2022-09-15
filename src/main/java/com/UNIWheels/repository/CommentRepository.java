package com.UNIWheels.repository;

import com.UNIWheels.entities.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;

// Creating a repository for the Comment class.
public interface CommentRepository extends MongoRepository<Comment, String> {

}