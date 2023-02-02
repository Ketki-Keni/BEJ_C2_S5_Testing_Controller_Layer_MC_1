/*
 * Author : Ketki Keni
 * Date : 30-01-2023
 * Created with : IntelliJ IDEA Community Edition
 */

package com.bej.service.service;

import com.bej.service.domain.Track;
import com.bej.service.exception.TrackAlreadyExistsException;
import com.bej.service.exception.TrackArtistNotFoundException;
import com.bej.service.exception.TrackNotFoundException;
import com.bej.service.exception.TrackRatingNotFoundException;
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
    public Track saveTrack(Track track) throws TrackAlreadyExistsException {
        if(trackRepository.findById(track.getTrackId()).isPresent()){
            throw new TrackAlreadyExistsException();
        }
        return trackRepository.save(track);
    }

    @Override
    public boolean deleteTrack(int trackId) throws TrackNotFoundException {
        boolean flag = false;
        if(trackRepository.findById(trackId).isEmpty())
        {
            throw new TrackNotFoundException();
        }
        else {
            Track track= trackRepository.findById(trackId).get();
            trackRepository.delete(track);
            flag = true;
        }
        return flag;
    }

    @Override
    public List<Track> getAllTracks() {
        return trackRepository.findAll();
    }

    @Override
    public List<Track> getTrackByRating(int trackRating) throws TrackRatingNotFoundException {
        if(trackRepository.findByTrackRating(trackRating).isEmpty())
        {
            throw new TrackRatingNotFoundException();
        }
        return trackRepository.findByTrackRating(trackRating);
    }

    @Override
    public List<Track> getTrackByArtist(String trackArtist) throws TrackArtistNotFoundException {
        if(trackRepository.findByTrackArtist(trackArtist).isEmpty())
        {
            throw new TrackArtistNotFoundException();
        }
        return trackRepository.findByTrackArtist(trackArtist);
    }
}
