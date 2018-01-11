package org.manas.eclipselink.service;

import org.manas.eclipselink.dao.ReviewsSummary;
import org.manas.eclipselink.entity.City;
import org.manas.eclipselink.entity.Hotel;
import org.manas.eclipselink.entity.Review;
import org.manas.eclipselink.entity.ReviewDetails;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface HotelService {

	Hotel getHotel(City city, String name);

	Page<Review> getReviews(Hotel hotel, Pageable pageable);

	Review getReview(Hotel hotel, int index);

	Review addReview(Hotel hotel, ReviewDetails details);

	ReviewsSummary getReviewSummary(Hotel hotel);

}