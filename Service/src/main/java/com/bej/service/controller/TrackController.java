/*
 * Author : Ketki Keni
 * Date : 30-01-2023
 * Created with : IntelliJ IDEA Community Edition
 */

package com.bej.service.controller;

import com.bej.service.domain.Track;
import com.bej.service.service.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class TrackController {
    TrackService trackService;

    @Autowired
    public TrackController(TrackService trackService) {
        this.trackService = trackService;
    }

    //Uri : http://localhost:8082/api/v1/track : Method : Post

    @PostMapping("/track")
    public ResponseEntity<?> addTrack(@RequestBody Track track){

        Track newtrack=trackService.saveTrack(track);
        System.out.println("== addTrack() Method == | New Track"+newtrack);

        if(newtrack!=null){
            return new ResponseEntity<Track>(newtrack, HttpStatus.CREATED);
        }
        else{
            return new ResponseEntity<String>("Error Occurred While Inserting",HttpStatus.NOT_FOUND);
        }
    }

    //Uri : http://localhost:8082/api/v1/tracks : Method : Get

    @GetMapping("/tracks")
    public ResponseEntity<?> getAllCustomers(){
        List<Track> allTracks=trackService.getAllTracks();

        if(allTracks.size()>0){
            return new ResponseEntity<List<Track>>(allTracks, HttpStatus.OK);
        }else {
            return new ResponseEntity<String>("No tracks Found",HttpStatus.NOT_FOUND);
        }
    }

    //Uri : http://localhost:8082/api/v1/trackbyrating/4 : Method : Get

    @GetMapping("/trackbyrating/{trackRating}")
    public ResponseEntity<?> getTrackByRating(@PathVariable int trackRating){
        List<Track> allTracks=trackService.getTrackByRating(trackRating);
        System.out.println("Get tracks by rating :"+trackRating);
        if(allTracks.size()>0){
            return new ResponseEntity<List<Track>>(allTracks, HttpStatus.OK);
        }else {
            return new ResponseEntity<String>("No Tracks Found",HttpStatus.NOT_FOUND);
        }
    }

    //Uri : http://localhost:8082/api/v1/trackbyartist/Justin Bieber : Method : Get

    @GetMapping("/trackbyartist/{trackArtist}")
    public ResponseEntity<?> getAllTrackByArtist(@PathVariable String trackArtist){
        List<Track> allTracks=trackService.getTrackByArtist(trackArtist);
        System.out.println("Get tracks by Artist name :"+trackArtist);
        if(allTracks.size()>0){
            return new ResponseEntity<List<Track>>(allTracks, HttpStatus.OK);
        }else {
            return new ResponseEntity<String>("No Tracks Found",HttpStatus.NOT_FOUND);
        }
    }

    //Uri : http://localhost:8082/api/v1/track/102 : Method : Delete

    @DeleteMapping("/track/{trackId}")
    public ResponseEntity<?> deleteTrack(@PathVariable int trackId){
        if(trackService.deleteTrack(trackId)){
            return new ResponseEntity<String>("Track Deleted",HttpStatus.OK);
        }else{
            return new ResponseEntity<String>("Error Occurred",HttpStatus.NOT_FOUND);
        }
    }

}
