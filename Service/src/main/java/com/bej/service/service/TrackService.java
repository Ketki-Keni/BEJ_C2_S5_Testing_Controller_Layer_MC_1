package com.bej.service.service;

import com.bej.service.domain.Track;

import java.util.List;

public interface TrackService {
    public Track saveTrack(Track track);
    public boolean deleteTrack(int trackId);
    public List<Track> getAllTracks();
    public List<Track> getTrackByRating(int trackRating);

    public List<Track> getTrackByArtist(String trackArtist);

}
