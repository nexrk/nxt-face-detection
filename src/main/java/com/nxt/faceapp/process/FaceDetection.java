/*
 *
 * Author: Radhakrishnan
 *
 */
package com.nxt.faceapp.process;

import java.io.File;
import java.io.FileNotFoundException;

import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.objdetect.CascadeClassifier;
import org.opencv.objdetect.Objdetect;
import org.springframework.util.ResourceUtils;

import com.nxt.faceapp.exception.StorageException;

import nu.pattern.OpenCV;

/**
 * The Class FaceDetection.
 */
public class FaceDetection {

	/**
	 * Load image.
	 *
	 * @param imagePath the image path
	 * @return the mat
	 */
	@SuppressWarnings("static-access")
	public static Mat loadImage(String imagePath) {
		Imgcodecs imageCodecs = new Imgcodecs();
		return imageCodecs.imread(imagePath);
	}

	/**
	 * Detect face.
	 *
	 * @param sourceImagePath the source image path
	 * 
	 * @return the integer
	 */
	public static Integer detectFace(String sourceImagePath) {
		OpenCV.loadShared();
		Mat loadedImage = loadImage(sourceImagePath);
		MatOfRect facesDetected = new MatOfRect();
		CascadeClassifier cascadeClassifier = new CascadeClassifier();
		int minFaceSize = Math.round(loadedImage.rows() * 0.1f);
		try {
			File file = ResourceUtils.getFile("classpath:haarcascades/haarcascade_frontalface_alt.xml");
			cascadeClassifier.load(file.getAbsolutePath());
		} catch (FileNotFoundException e) {
			throw new StorageException("Failed to load haarcacade file");

		}

		cascadeClassifier.detectMultiScale(loadedImage, facesDetected, 1.1, 3, Objdetect.CASCADE_SCALE_IMAGE,
				new Size(minFaceSize, minFaceSize), new Size());
		return facesDetected.toArray().length;

	}

}
