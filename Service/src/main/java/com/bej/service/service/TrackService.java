package com.bej.service.service;

import com.bej.service.domain.Track;
import com.bej.service.exception.TrackAlreadyExistsException;
import com.bej.service.exception.TrackArtistNotFoundException;
import com.bej.service.exception.TrackNotFoundException;
import com.bej.service.exception.TrackRatingNotFoundException;

import java.util.List;

public interface TrackService {
    public Track saveTrack(Track track) throws TrackAlreadyExistsException;
    public boolean deleteTrack(int trackId) throws TrackNotFoundException;
    public List<Track> getAllTracks();
    public List<Track> getTrackByRating(int trackRating) throws TrackRatingNotFoundException;

    public List<Track> getTrackByArtist(String trackArtist) throws TrackArtistNotFoundException;

}
