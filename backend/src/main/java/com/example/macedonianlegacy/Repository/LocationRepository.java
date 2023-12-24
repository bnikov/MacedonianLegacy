package com.example.macedonianlegacy.Repository;

import com.example.macedonianlegacy.Model.Location;
import com.example.macedonianlegacy.misc.RequestResponse.LocationSearchRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {
    List<Location> findAllByCategory(String category);

    @Query("SELECT l FROM Location l " +
            "WHERE (:#{#request.name} IS NULL OR LOWER(l.name) LIKE LOWER(concat('%', :#{#request.name}, '%'))) ")
    List<Location> search(@Param("request") LocationSearchRequest request);
}
