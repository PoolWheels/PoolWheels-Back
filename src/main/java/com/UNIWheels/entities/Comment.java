package com.UNIWheels.entities;

import com.UNIWheels.dto.CommentDto;
import com.UNIWheels.enums.CommentTypeEnum;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document
// A class that is used to create a comment object.
public class Comment {

    @Id
    private String id;
    private String user;
    private Date creationDate;
    private Date modificationDate;
    private String description;
    private String type;

    public Comment() {
        this.id = String.valueOf((int)(Math.random()*5));
    }

    public Comment(CommentDto commentDto) {
        this.id = commentDto.getId();
        this.user = commentDto.getUser().toString();
        this.creationDate = commentDto.getCreationDate();
        this.modificationDate = commentDto.getModificationDate();
        this.description = commentDto.getDescription();
        this.type = commentDto.getType().toString();
    }

    public Comment(String user, Date creationDate, Date modificationDate, String description, String type) {
        this();
        this.user = user;
        this.creationDate = creationDate;
        this.modificationDate = modificationDate;
        this.description = description;
        this.type = type;
    }

    public Comment(String id, String user, Date creationDate, Date modificationDate, String description, String type) {
        this(user, creationDate, modificationDate, description, type);
        this.id = id;
    }

    /**
     * This function returns the id of the current object
     *
     * @return The id of the object.
     */
    public String getId() {
        return id;
    }

    /**
     * This function sets the id of the object to the id passed in as a parameter
     *
     * @param id The id of the user.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * This function returns the user
     *
     * @return The user name.
     */
    public String getUser() {
        return user;
    }

    /**
     * This function sets the user variable to the value of the user parameter.
     *
     * @param user The user name to use when connecting to the database.
     */
    public void setUser(String user) {
        this.user = user;
    }

    /**
     * > This function returns the creation date of the object
     *
     * @return The creation date of the object.
     */
    public Date getCreationDate() {
        return creationDate;
    }

    /**
     * This function sets the creation date of the object
     *
     * @param creationDate The date the user was created.
     */
    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    /**
     * > Returns the date the file was last modified
     *
     * @return The modification date of the file.
     */
    public Date getModificationDate() {
        return modificationDate;
    }

    /**
     * This function sets the modification date of the file
     *
     * @param modificationDate The date the file was last modified.
     */
    public void setModificationDate(Date modificationDate) {
        this.modificationDate = modificationDate;
    }

    /**
     * > This function returns the description of the object
     *
     * @return The description of the item.
     */
    public String getDescription() {
        return description;
    }

    /**
     * This function sets the description of the object to the value of the parameter description.
     *
     * @param description The description of the event.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * This function returns the type of the current object
     *
     * @return The type of the object.
     */
    public String getType() {
        return type;
    }

    /**
     * This function sets the type of the object to the type passed in.
     *
     * @param type The type of the event.
     */
    public void setType(String type) {
        this.type = type;
    }

}
