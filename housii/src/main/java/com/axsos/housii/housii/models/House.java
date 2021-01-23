package com.axsos.housii.housii.models;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "houses")
public class House {
    @Id
    @GeneratedValue
    private Long id;
    @NotBlank(message = "House name must not be blank")
    @Size(min = 3, max = 255, message = "House name must be letters and at least 3 characters")
    private String name;
    @NotBlank(message = "location name must not be blank")
    @Size(min = 3, message = "location must be letters and at least 3 characters")
    private String location;
    @NotBlank(message = "description must not be blank")
    @Size(min = 10, message = "description must be at least 10 characters")
    private String description;
    @DecimalMin(value = "1.0", message = "Price must be at least one")
    private double price;
    @Future(message = "must be renting in the future")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;
    private Integer estimatedDate;
    private Integer avgRate;
    @OneToMany(mappedBy = "house", fetch = FetchType.LAZY)
    private List<Rating> ratings;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;
    @Column(updatable = false)
    private Date createdAt;
    private Date updatedAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = new Date();
    }


    public House() {
    }

    public House(@NotBlank(message = "House name must not be blank") @Size(min = 3, max = 255, message = "House name must be letters and at least 3 characters") String name, @NotBlank(message = "location name must not be blank") @Size(min = 3, message = "location must be letters and at least 3 characters") String location, @NotBlank(message = "description must not be blank") @Size(min = 10, message = "description must be at least 10 characters") String description, @DecimalMin(value = "1.0", message = "Price must be at least one") double price) {
        this.name = name;
        this.location = location;
        this.description = description;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getEstimatedDate() {
        return estimatedDate;
    }

    public void setEstimatedDate(Integer estimatedDate) {
        this.estimatedDate = estimatedDate;
    }

    public Integer getAvgRate() {
        return avgRate;
    }

    public void setAvgRate(Integer avgRate) {
        this.avgRate = avgRate;
    }

    public List<Rating> getRatings() {
        return ratings;
    }

    public void setRatings(List<Rating> ratings) {
        this.ratings = ratings;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}


