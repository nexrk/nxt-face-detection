/*
 *
 * Author: Radhakrishnan
 *
 */
package com.nxt.faceapp.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.nxt.faceapp.service.FaceAppService;

/**
 * The Class FaceAppController.
 */
@RestController
public class FaceAppController {

	/** The face app service. */
	@Autowired
	FaceAppService faceAppService;

	/**
	 * Face detection.
	 *
	 * @param image the image
	 * @return the integer
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@PostMapping("/facedetection")
	public ResponseEntity<String> faceDetection(@RequestParam("image") MultipartFile image) throws IOException {
		String response = String.valueOf(faceAppService.getFaceDetectionCount(image));
	    return new ResponseEntity<String>(response, HttpStatus.CREATED);

	}
}
