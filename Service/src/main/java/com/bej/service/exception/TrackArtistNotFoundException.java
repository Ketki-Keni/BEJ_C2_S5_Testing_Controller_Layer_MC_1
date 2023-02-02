/*
 * Author : Ketki Keni
 * Date : 02-02-2023
 * Created with : IntelliJ IDEA Community Edition
 */

package com.bej.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code= HttpStatus.ACCEPTED.NOT_FOUND , reason = "Artist Name for the specified track not found")
public class TrackArtistNotFoundException extends Exception{
}
