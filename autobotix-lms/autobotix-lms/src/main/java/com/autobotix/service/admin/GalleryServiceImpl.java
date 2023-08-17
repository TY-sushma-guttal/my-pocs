package com.autobotix.service.admin;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.autobotix.dto.DeleteImageDto;
import com.autobotix.dto.GalleryDto;
import com.autobotix.entity.Gallery;
import com.autobotix.exceptions.DeleteException;
import com.autobotix.exceptions.EmptyFileException;
import com.autobotix.firebase.DeleteFromCloud;
import com.autobotix.firebase.UploadToCloud;
import com.autobotix.repository.GalleryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GalleryServiceImpl implements GalleryService {
	
	private final UploadToCloud toCloud;
	private final DeleteFromCloud deleteFromCloud;
	private final GalleryRepository galleryRepository;

	@Override
	public String addToGallery(List<MultipartFile> galleryImages, GalleryDto galleryDto) {
		if (galleryImages.isEmpty()) {
			throw new EmptyFileException("No File Found");
		}

		List<String> uploadedImages = new ArrayList<>();
		for (MultipartFile multipartFile : galleryImages) {
			uploadedImages.add(toCloud.uploadImage(multipartFile));
		}
		for (String string : uploadedImages) {
			System.err.println(string);
		}
		Gallery gallery = new Gallery();
		System.err.println("Gallery object created");
		gallery.setImages(uploadedImages);
		System.err.println("uploaded images set to setImages");
		gallery.setDate(galleryDto.getDate());
		
		galleryRepository.save(gallery);
		System.err.println("data saved in gallery");
		return "photos added successfully";

	}

	@Override
	public String deleteImage(DeleteImageDto deleteImage) {
		if (deleteImage == null) {
			throw new EmptyFileException("No File Found");
		}
		if (deleteFromCloud.deleteFile(deleteImage.getPath())) {
			return "delete successfull";
		}
		throw new DeleteException("Delete Unsuccessfull");
	}

}
