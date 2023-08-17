package com.autobotix.service.admin;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.autobotix.dto.DeleteImageDto;
import com.autobotix.dto.GalleryDto;

public interface GalleryService {
	
	String addToGallery(List<MultipartFile> galleryImages, GalleryDto galleryDto);

	String deleteImage(DeleteImageDto deleteImage);

}
