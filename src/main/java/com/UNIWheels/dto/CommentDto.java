package com.UNIWheels.dto;

import com.UNIWheels.entities.User;
import com.UNIWheels.enums.CommentTypeEnum;

import java.util.Date;

// A class that is used to transfer data between the client and the server.
public class CommentDto {

    private String id;
    private String user;
    private Date creationDate;
    private Date modificationDate;
    private String description;
    private CommentTypeEnum type;

    public CommentDto() {
    }

    public CommentDto(String user, Date creationDate, Date modificationDate, String description, CommentTypeEnum type) {
        this();
        this.user = user;
        this.creationDate = creationDate;
        this.modificationDate = modificationDate;
        this.description = description;
        this.type = type;
    }

    public CommentDto(String id, String user, Date creationDate, Date modificationDate, String description, CommentTypeEnum type) {
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
     * This function returns the user.
     *
     * @return The user object.
     */
    public String getUser() {
        return user;
    }

    /**
     * This function sets the user variable to the user variable passed in.
     *
     * @param user The user object that will be used to create the new user.
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
     * > This function returns the type of the comment
     *
     * @return The type of the comment.
     */
    public CommentTypeEnum getType() {
        return type;
    }

    /**
     * > Sets the type of the comment
     *
     * @param type The type of comment.
     */
    public void setType(CommentTypeEnum type) {
        this.type = type;
    }


}

