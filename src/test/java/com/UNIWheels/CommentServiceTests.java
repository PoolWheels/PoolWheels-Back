package com.UNIWheels;

import com.UNIWheels.entities.Comment;
import com.UNIWheels.service.CommentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

@SpringBootTest
public class CommentServiceTests {

    @Autowired
    private CommentService commentService;

    @Test
    void contextLoads() {
    }

    @Test
    void createCommentTest(){
        Comment newComment = null;
        try {
            //Create a new comment
            newComment = new Comment("1", "David", new SimpleDateFormat("dd/MM/yyyy").parse("23/03/2022"),
                    new SimpleDateFormat("dd/MM/yyyy").parse("23/06/2022"), "Test", "SUGGESTION");
            //Insert the new comment in MongoDB
            commentService.create(newComment);
            //Check if the used was created in db
            Comment commentVerify = commentService.findById("1");
            //Assert
            assertEquals(newComment, commentVerify);
        } catch (ParseException e) {
            fail("Fail");
        }
    }
}
