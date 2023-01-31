/*
 * Author : Ketki Keni
 * Date : 30-01-2023
 * Created with : IntelliJ IDEA Community Edition
 */

package com.bej.service.service;

import com.bej.service.domain.Track;
import com.bej.service.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrackServiceImpl implements TrackService{
    TrackRepository trackRepository;

    @Autowired
    public TrackServiceImpl(TrackRepository trackRepository) {
        this.trackRepository = trackRepository;
    }

    @Override
    public Track saveTrack(Track track) {
        return trackRepository.save(track);
    }

    @Override
    public boolean deleteTrack(int trackId) {
        if(trackRepository.findById(trackId).isPresent()){
            Track track= trackRepository.findById(trackId).get();
            trackRepository.delete(track);
            return true;
        }
        return false;
    }

    @Override
    public List<Track> getAllTracks() {
        return trackRepository.findAll();
    }

    @Override
    public List<Track> getTrackByRating(int trackRating) {
        return trackRepository.findByTrackRating(trackRating);
    }

    @Override
    public List<Track> getTrackByArtist(String trackArtist) {
        return trackRepository.findByTrackArtist(trackArtist);
    }
}
