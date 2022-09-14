package com.UNIWheels.entities;

import com.UNIWheels.enums.CommentTypeEnum;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document
public class Comment {

    @Id
    private String id;
    private String user;
    private Date creationDate;
    private Date modificationDate;
    private String description;
    private String type;

    public Comment() {
    }

    public Comment(String id, String user, Date creationDate, Date modificationDate, String description, String type) {
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

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
