package com.techhub.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techhub.demo.Exceptions.UserNotFoundException;
import com.techhub.demo.Model.ReviewModel;
import com.techhub.demo.Model.deliveryModel;
import com.techhub.demo.Service.ReviewService;

@RestController
@RequestMapping("/Retail-M-System/review")
public class ReviewController {
	@Autowired
	@Qualifier("review")
	private ReviewService Rservice;

	@PostMapping("/addreview")
	public String addReview(@RequestBody ReviewModel delivery) {
		boolean b = Rservice.addReview(delivery);
		return b ? "New review Added" : "review is not Added";
	}

	@PutMapping("/updatereview")
	public String updateReview(@RequestBody ReviewModel review) {
		boolean updated = Rservice.updateReview(review);

		if (updated) {
			return "Review updated successfully.";
		} else {
			throw new UserNotFoundException("Review not found or not updated.");
		}
	}

	@GetMapping("/getReviewByProductId/{id}")
	public List<ReviewModel> getReviews(@PathVariable int id) {
		List<ReviewModel> reviews = Rservice.getReviewsByProductId(id);

		if (reviews.isEmpty()) {
			throw new UserNotFoundException("Review not found or not updated.");
		}

		return reviews;
	}

	@DeleteMapping("/Delreview/{id}")
	public String deleteReview(@PathVariable("id") int reviewId) {
		boolean deleted = Rservice.deleteReview(reviewId);

		if (deleted) {
			return "Review deleted successfully.";
		} else {
			throw new UserNotFoundException("Review with ID " + reviewId + " not found.");
		}
	}

}
