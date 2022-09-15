package com.UNIWheels.repository;

import com.UNIWheels.entities.Comment;
import com.UNIWheels.entities.User;

import org.springframework.data.mongodb.repository.MongoRepository;

// Creating a repository for the Comment class.
public interface CommentRepository extends MongoRepository<Comment, String> {

}