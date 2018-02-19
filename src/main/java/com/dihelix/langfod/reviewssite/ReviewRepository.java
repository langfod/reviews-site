package com.dihelix.langfod.reviewssite;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

@Component
public class ReviewRepository {

	Map<Long, Review> reviews = new HashMap<>();

	public ReviewRepository(Collection<Review> listOfReviews) {
		if (listOfReviews != null) addReviews(listOfReviews);
	}

	void addReview(Review review) {
		this.reviews.put(review.getId(), review);
	}

	void addReviews(Collection<Review> reviews) {
		reviews.forEach(r -> addReview(r));
	}

	public Collection<Review> findAll() {
		return reviews.values();
	}

	public Collection<String> findAllTags() {
		return reviews.values().stream().map(r -> r.getTags()).flatMap(Collection::stream).collect(Collectors.toSet());
	}

	public Collection<Review> findByTag(String tag) {
		return reviews.values().stream().filter(r -> r.getTags().contains(tag)).collect(Collectors.toList());
	}

	public Review findOne(Long id) {
		return reviews.get(id);
	}

}
