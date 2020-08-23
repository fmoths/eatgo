package com.fmoths.eatgo.application;

import com.fmoths.eatgo.domain.Review;
import com.fmoths.eatgo.domain.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;

    @Autowired
    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    public Review addReview(Review review){
        return reviewRepository.save(review);
    }
}
