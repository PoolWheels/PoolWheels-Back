package com.UNIWheels.dto;

import com.UNIWheels.entities.User;
import com.UNIWheels.enums.CommentTypeEnum;

import java.util.Date;

public class CommentDto {

    private String id;
    private User user;
    private Date creationDate;
    private Date modificationDate;
    private String description;
    private CommentTypeEnum type;

    public CommentDto() {
    }

    public CommentDto(String id, User user, Date creationDate, Date modificationDate, String description, CommentTypeEnum type) {
        this.id = id;
        this.user = user;
        this.creationDate = creationDate;
        this.modificationDate = modificationDate;
        this.description = description;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getModificationDate() {
        return modificationDate;
    }

    public void setModificationDate(Date modificationDate) {
        this.modificationDate = modificationDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public CommentTypeEnum getType() {
        return type;
    }

    public void setType(CommentTypeEnum type) {
        this.type = type;
    }
}

