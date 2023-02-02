/*
 * Author : Ketki Keni
 * Date : 02-02-2023
 * Created with : IntelliJ IDEA Community Edition
 */

package com.bej.service.service;

import com.bej.service.domain.Track;
import com.bej.service.exception.TrackAlreadyExistsException;
import com.bej.service.exception.TrackArtistNotFoundException;
import com.bej.service.exception.TrackNotFoundException;
import com.bej.service.exception.TrackRatingNotFoundException;
import com.bej.service.repository.TrackRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class TrackServiceImplTest {

    @Mock
    private TrackRepository trackRepository;
    @InjectMocks
    private TrackServiceImpl trackService;

    Track track, track1;
    List<Track> trackList;

    @BeforeEach
    void setInitialization(){
        track = new Track(102, "Paradise", "Album: Mylo Xyloto", 4, "Coldplay");
        track1 = new Track(101, "Bones", "Genre: Rock Album: Mercury – Acts 1 & 2", 5, "Imagine Dragons");
        trackList = Arrays.asList(track,track1);
    }

    @AfterEach
    void tearDown(){
        track = null;
        track1 = null;
    }

    @Test
    @DisplayName("Test Case for Saving Track Object")
    public void givenTrackToSaveReturnSavedTrackSuccess() throws TrackAlreadyExistsException {
        when(trackRepository.findById(track.getTrackId())).thenReturn(Optional.ofNullable(null));
        when(trackRepository.save(any())).thenReturn(track);
        assertEquals(track, trackService.saveTrack(track));
        verify(trackRepository,times(1)).save(any());
        verify(trackRepository,times(1)).findById(any());
    }

    @Test
    @DisplayName("Test case for retrieving all the track object")
    public void givenTrackReturnAllTrackDetailsSuccess(){
        when(trackRepository.findById(track.getTrackId())).thenReturn(Optional.ofNullable(track));
        assertThrows(TrackAlreadyExistsException.class,()->trackService.saveTrack(track));
        verify(trackRepository,times(0)).save(any());
        verify(trackRepository,times(1)).findById(any());
    }

    @Test
    @DisplayName("Test case for retrieving all the track object")
    public void givenTrackReturnAllTrackDetailsFailure(){
        when(trackRepository.findAll()).thenReturn(trackList);
        assertEquals(trackList, trackService.getAllTracks());
        verify(trackRepository,times(1)).findAll();
    }

    @Test
    @DisplayName("Test case for retrieving all the tracks by Artist")
    public void givenTrackArtistReturnAllTrackDetailsSuccess() throws TrackArtistNotFoundException {
        when(trackRepository.findByTrackArtist(track.getTrackArtist())).thenReturn(trackList);
        List<Track> list = trackRepository.findByTrackArtist(track.getTrackArtist());
        assertEquals("Coldplay", list.get(0).getTrackArtist());
        verify(trackRepository, times(1)).findByTrackArtist(any());
    }

    @Test
    @DisplayName("Test case for retrieving all the tracks by Artist")
    public void givenTrackArtistReturnAllTrackDetailsFailure() throws TrackArtistNotFoundException{
        when(trackRepository.findByTrackArtist(track.getTrackArtist())).thenReturn(trackList);
        List<Track> list = trackRepository.findByTrackArtist(track.getTrackArtist());
        assertNotEquals("Baby", list.get(0).getTrackArtist());
        verify(trackRepository, times(1)).findByTrackArtist(any());
    }

    @Test
    @DisplayName("Test case for deleting Track object")
    public void givenTrackToDeleteShouldDeleteTrack() throws TrackNotFoundException {
        when(trackRepository.findById(track.getTrackId())).thenReturn(Optional.ofNullable(track));
        boolean flag = trackService.deleteTrack(track.getTrackId());
        assertEquals(true,flag);

        verify(trackRepository,times(1)).deleteById(any());
        verify(trackRepository,times(1)).findById(any());
    }
}
