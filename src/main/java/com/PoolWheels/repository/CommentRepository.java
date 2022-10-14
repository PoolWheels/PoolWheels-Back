package com.PoolWheels.repository;

import com.PoolWheels.entities.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;

// Creating a repository for the Comment class.
public interface CommentRepository extends MongoRepository<Comment, String> {

}