package com.autobotix.controller.admin;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.autobotix.dto.DeleteImageDto;
import com.autobotix.dto.GalleryDto;
import com.autobotix.response.AppResponse;
import com.autobotix.service.admin.GalleryService;
import com.autobotix.util.ConstantValues;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/gallery")
public class GalleryController {

	private final GalleryService galleryService;
	
	@PostMapping("/")
	public ResponseEntity<AppResponse> addToGallery(
			@RequestParam(value = "galleryImage") List<MultipartFile> galleryImages,
			@RequestPart("dateData") GalleryDto galleryDto) {
		return new ResponseEntity<>(AppResponse.builder().error(false).message(ConstantValues.SUCCESS)
				.data(galleryService.addToGallery(galleryImages, galleryDto)).build(), HttpStatus.OK);
	}

	@DeleteMapping("/")
	public ResponseEntity<AppResponse> deleteImage(@RequestBody DeleteImageDto deleteImage) {
		return new ResponseEntity<>(AppResponse.builder().error(false).message(ConstantValues.SUCCESS)
				.data(galleryService.deleteImage(deleteImage)).build(), HttpStatus.OK);

	}
}
