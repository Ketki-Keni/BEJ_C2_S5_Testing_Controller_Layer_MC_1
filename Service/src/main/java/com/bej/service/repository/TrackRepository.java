package com.bej.service.repository;

import com.bej.service.domain.Track;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface TrackRepository extends MongoRepository<Track,Integer> {
    @Query("{'trackRating':{$gt : 4}}")
    List<Track> findByTrackRating(int trackRating);
    @Query("{'trackArtist':{$in : [?0]}}")
    List<Track> findByTrackArtist(String trackArtist);
}
