package com.techhub.demo.Model;

import java.security.Timestamp;

import lombok.Data;

@Data
public class ReviewModel {
    private int reviewId;
    private int userId;
    private int productId;
    private int rating;
    private String comment;
    private String reviewDate;
	private RegistrationModel registration;
	 private ProductModel product;
}

