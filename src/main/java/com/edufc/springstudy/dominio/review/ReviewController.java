package com.edufc.springstudy.dominio.review;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.edufc.springstudy.util.ControllerUtil;

@Controller
@RequestMapping("/reviews")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @PostMapping
    public ResponseEntity<Review> addReview(@RequestBody Review Review) {
        var response = this.reviewService.addReview(Review);

        return ResponseEntity.created(ControllerUtil.getLocation(response.id())).build();
    }

    @GetMapping
    public ResponseEntity<List<Review>> getAllReviews() {
        var response = this.reviewService.getAllReviews();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Review> getReview(@PathVariable final int id) {
        var response = this.reviewService.getReview(id);

        if (response.isPresent())
            return ResponseEntity.ok(response.get());
        else
            return ResponseEntity.notFound().build();
    }

    @PutMapping
    public ResponseEntity<Review> updateReview(@RequestBody Review Review) {
        this.reviewService.updateReview(Review);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Review> deleteReview(@PathVariable final int id) {
        var isDeleted = this.reviewService.deleteReview(id);

        if (isDeleted)
            return ResponseEntity.noContent().build();
        else
            return ResponseEntity.notFound().build();
    }
}
