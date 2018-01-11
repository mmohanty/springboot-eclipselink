package org.manas.eclipselink.dao;

import java.util.List;

import org.manas.eclipselink.entity.City;
import org.manas.eclipselink.entity.Hotel;
import org.manas.eclipselink.entity.HotelSummary;
import org.manas.eclipselink.entity.RatingCount;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

public interface HotelRepository extends Repository<Hotel, Long> {

	Hotel findByCityAndName(City city, String name);

	@Query("select new org.manas.eclipselink.entity.HotelSummary(h.city, h.name, avg(r.rating)) "
			+ "from Hotel h left outer join h.reviews r where h.city = ?1 group by h")
	Page<HotelSummary> findByCity(City city, Pageable pageable);

	@Query("select new org.manas.eclipselink.entity.RatingCount(r.rating, count(r)) "
			+ "from Review r where r.hotel = ?1 group by r.rating order by r.rating DESC")
	List<RatingCount> findRatingCounts(Hotel hotel);
}
