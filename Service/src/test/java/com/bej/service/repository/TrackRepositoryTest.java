/*
 * Author : Ketki Keni
 * Date : 02-02-2023
 * Created with : IntelliJ IDEA Community Edition
 */

package com.bej.service.repository;

import com.bej.service.domain.Track;
import com.bej.service.exception.TrackAlreadyExistsException;
import com.bej.service.exception.TrackArtistNotFoundException;
import com.bej.service.exception.TrackNotFoundException;
import com.bej.service.exception.TrackRatingNotFoundException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataMongoTest
public class TrackRepositoryTest {

    @Autowired
    private TrackRepository trackRepository;
    private Track track, track1;

    @BeforeEach
    void setInitialization(){
        track = new Track(102, "Paradise", "Album: Mylo Xyloto", 4, "Coldplay");
    }

    @AfterEach
    void tearDown(){
        track = null;
        track1 = null;
    }

    @Test
    @DisplayName("Test Case for Saving Track Object")
    public void givenTrackToSaveReturnSavedTrackSuccess() throws TrackAlreadyExistsException {
        trackRepository.save(track);
        Track track1 = trackRepository.findById(track.getTrackId()).get();
        assertNotNull(track1);
        assertEquals(track.getTrackId(), track1.getTrackId());
    }

    @Test
    @DisplayName("Test case for retrieving all the track object")
    public void givenTrackReturnAllTrackDetailsSuccess(){
        trackRepository.insert(track);

        List<Track> list = trackRepository.findAll();
        assertEquals(2, list.size());
        assertEquals("Paradise", list.get(1).getTrackName());
    }

    @Test
    @DisplayName("Test case for retrieving all the track object")
    public void givenTrackReturnAllTrackDetailsFailure(){
        List<Track> list = trackRepository.findAll();
        assertNotEquals(1, list.size());
        assertNotEquals("Bones", list.get(1).getTrackName());
    }
    @Test
    @DisplayName("Test case for retrieving all the tracks greater than specified rating")
    public void givenTrackRatingReturnAllTrackDetailsSuccess() throws TrackRatingNotFoundException {
        List<Track> list1 = trackRepository.findByTrackRating(4);
        assertEquals("Bones",list1.get(0).getTrackName());
    }

    @Test
    @DisplayName("Test case for retrieving all the tracks greater than specified rating")
    public void givenTrackRatingReturnAllTrackDetailsFailure() throws TrackRatingNotFoundException{
        List<Track> list = trackRepository.findByTrackRating(4);
        assertNotEquals("Paradise",list.get(0).getTrackName());
    }

    @Test
    @DisplayName("Test case for retrieving all the tracks by Artist")
    public void givenTrackArtistReturnAllTrackDetailsSuccess() throws TrackArtistNotFoundException {
        List<Track> list1 = trackRepository.findByTrackArtist("Coldplay");
        assertEquals("Paradise",list1.get(0).getTrackName());
    }
    //
    @Test
    @DisplayName("Test case for retrieving all the tracks by Artist")
    public void givenTrackArtistReturnAllTrackDetailsFailure() throws TrackArtistNotFoundException{
        List<Track> list = trackRepository.findByTrackArtist("Coldplay");
        assertNotEquals("Bones",list.get(0).getTrackName());
    }

    @Test
    @DisplayName("Test case for deleting Track object")
    public void givenTrackToDeleteShouldDeleteTrack() throws TrackNotFoundException {
        trackRepository.insert(track);
        Track track1 = trackRepository.findById(track.getTrackId()).get();
        trackRepository.delete(track1);
        assertEquals(Optional.empty(), trackRepository.findById(track.getTrackId()));
    }
}
