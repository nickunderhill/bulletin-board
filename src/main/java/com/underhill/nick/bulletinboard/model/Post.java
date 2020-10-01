package com.underhill.nick.bulletinboard.model;

import org.hibernate.validator.constraints.URL;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
@Table(name = "posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 2, max = 70, message = "Title must be between 2 and 70 characters")
    private String title;

    @NotNull
    @Size(min = 2, max = 200, message = "Description must be between 2 and 200 characters")
    private String description;

    @URL
    private String image;

    @Column(name="when_add", columnDefinition = "DATETIME")
    private LocalDateTime whenAdd;

    @ManyToOne(cascade = {CascadeType.PERSIST})
    @JoinColumn(name = "author_id")
    private User author;

    public Post() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public LocalDateTime getWhenAdd() {
        return whenAdd;
    }

    public void setWhenAdd(LocalDateTime whenAdd) {
        this.whenAdd = whenAdd;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }
}
