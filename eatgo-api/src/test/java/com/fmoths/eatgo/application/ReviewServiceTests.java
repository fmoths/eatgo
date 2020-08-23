package com.fmoths.eatgo.application;

import com.fmoths.eatgo.domain.Review;
import com.fmoths.eatgo.domain.ReviewRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

class ReviewServiceTests {


    private ReviewService reviewService;

    @Mock
    private ReviewRepository reviewRepository;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.initMocks(this);
        reviewService = new ReviewService(reviewRepository);
    }

    @Test
    public void addReview(){
        Review review = Review.builder()
                .name("kdpark")
                .score(1)
                .description("good")
                .build();

        reviewService.addReview(review);

        verify(reviewRepository).save(any());
    }
}