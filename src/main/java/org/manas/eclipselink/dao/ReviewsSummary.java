package org.manas.eclipselink.dao;

import org.manas.eclipselink.entity.Rating;

public interface ReviewsSummary {

	long getNumberOfReviewsWithRating(Rating rating);

}
