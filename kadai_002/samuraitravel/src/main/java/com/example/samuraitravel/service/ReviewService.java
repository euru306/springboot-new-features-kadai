package com.example.samuraitravel.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.samuraitravel.entity.House;
import com.example.samuraitravel.entity.Review;
import com.example.samuraitravel.entity.User;
import com.example.samuraitravel.form.ReviewForm;
import com.example.samuraitravel.repository.ReviewRepository;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;

    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    public Optional<Review> findReviewById(Integer id) {
        return reviewRepository.findById(id);
    }

    public List<Review> findTop6ReviewsByHouseOrderByCreatedAtDesc(House house) {
        return reviewRepository.findTop6ByHouseOrderByCreatedAtDesc(house);
    }

    public Review findReviewByHouseAndUser(House house, User user) {
        return reviewRepository.findByHouseAndUser(house, user);
    }

    public long countReviewsByHouse(House house) {
        return reviewRepository.countByHouse(house);
    }

    public Page<Review> findReviewsByHouseOrderByCreatedAtDesc(House house, Pageable pageable) {
        return reviewRepository.findByHouseOrderByCreatedAtDesc(house, pageable);
    }

    @Transactional
    public void createReview(ReviewForm reviewForm, House house, User user) {
        Review review = new Review();
        review.setHouse(house);
        review.setUser(user);
        review.setScore(reviewForm.getScore());
        review.setContent(reviewForm.getContent());
        reviewRepository.save(review);
    }

    @Transactional
    public void updateReview(ReviewForm reviewForm, Review review) {
        review.setScore(reviewForm.getScore());
        review.setContent(reviewForm.getContent());
        reviewRepository.save(review);
    }

    @Transactional
    public void deleteReview(Review review) {
        reviewRepository.delete(review);
    }

    public boolean hasUserAlreadyReviewed(House house, User user) {
        return reviewRepository.findByHouseAndUser(house, user) != null;
    }
}

