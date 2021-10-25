/*
 *
 * Author: Radhakrishnan
 *
 */
package com.nxt.faceapp.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.nxt.faceapp.exception.StorageException;
import com.nxt.faceapp.process.FaceDetection;

/**
 * The Class FaceAppService.
 */
@Service
public class FaceAppService {
	
	/** The logger. */
	Logger logger = LoggerFactory.getLogger(FaceAppService.class);
	
    @Value("${uploadpath}")
    private String path;

	/**
	 * Gets the face detection count.
	 *
	 * @param image the image
	 * @param name the name
	 * @return the face detection count
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public Integer getFaceDetectionCount(MultipartFile file) throws IOException {
		
		if (file.isEmpty()) {

			throw new StorageException("Failed to store empty file");
		}

		try {
			var fileName = file.getOriginalFilename();
			var is = file.getInputStream();

			Files.copy(is, Paths.get(path + fileName), StandardCopyOption.REPLACE_EXISTING);
			File sourceFile = new File(Paths.get(path + fileName).toUri());
			return FaceDetection.detectFace(sourceFile.getAbsolutePath());

		} catch (IOException e) {

			var msg = String.format("Failed to store file %f", file.getName());

			throw new StorageException(msg, e);
		}

	}

	

}

