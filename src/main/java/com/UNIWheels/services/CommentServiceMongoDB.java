package com.UNIWheels.services;

import com.UNIWheels.entities.Comment;
import com.UNIWheels.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceMongoDB implements CommentService{

    private final CommentRepository commentRepository;

    public CommentServiceMongoDB(@Autowired CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public Comment create(Comment comment) {
        commentRepository.insert(comment);
        return comment;
    }

    @Override
    public Comment findById(String id) {
        return commentRepository.findById(id).orElse(null);
    }

    @Override
    public List<Comment> getAll() {
        return commentRepository.findAll();
    }

    @Override
    public void deleteById(String id) {
        commentRepository.deleteById(id);
    }

    @Override
    public Comment update(Comment comment, String id) {
        if (commentRepository.existsById(id)){
            commentRepository.deleteById(id);
            commentRepository.insert(comment);
        }
        return comment;
    }
}
