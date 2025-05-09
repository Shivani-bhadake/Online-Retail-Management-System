package com.techhub.demo.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techhub.demo.Model.ReviewModel;
import com.techhub.demo.Repository.ReviewRepository;

@Service("review")
public class ReviewService {
	@Autowired
	private ReviewRepository repo;

	public boolean addReview(ReviewModel review) {
		return repo.addReview(review);

	}

	public boolean updateReview(ReviewModel review) {
		return repo.updateReview(review);
	}

	public List<ReviewModel> getReviewsByProductId(int productId) {
		return repo.getReviewsByProductId(productId);
	}

	public boolean deleteReview(int reviewId) {
		return repo.deleteReview(reviewId);
	}
}
