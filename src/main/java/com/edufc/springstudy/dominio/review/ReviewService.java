package com.edufc.springstudy.dominio.review;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;

    public List<Review> getAllReviews() {
        var entitys = this.reviewRepository.findAll();

        return ReviewMapper.valueOf(entitys);
    }

    public Optional<Review> getReview(final int id) {
        return this.reviewRepository.findById(id).map(ReviewMapper::valueOf);
    }

    @Transactional
    public Review addReview(final Review dto) {
        var entity = this.reviewRepository.save(ReviewMapper.valueOf(dto));

        return ReviewMapper.valueOf(entity);
    }

    @Transactional
    public void updateReview(final Review dto) {
        var entity = this.reviewRepository.findById(dto.id());


        if (entity.isPresent()) {
            ReviewMapper.merge(entity.get(), dto);
            this.reviewRepository.save(entity.get());
        }
    }

    @Transactional
    public boolean deleteReview(final int id) {
        var entity = this.reviewRepository.findById(id);

        if (entity.isPresent()) {
            this.reviewRepository.delete(entity.get());
            return true;
        } else {
            return false;
        }
    }
}