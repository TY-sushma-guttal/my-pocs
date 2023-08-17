package com.autobotix.controller.admin;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.autobotix.dto.FeedsDto;
import com.autobotix.response.AppResponse;
import com.autobotix.service.admin.FeedService;
import com.autobotix.util.ConstantValues;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/feed")
public class FeedsController {
	
	private final FeedService feedService;
	
	@PostMapping("")
	public ResponseEntity<AppResponse> addFeed(@RequestParam(value = "image") MultipartFile image,
			@RequestPart("data") FeedsDto feedsDto) {
		return new ResponseEntity<>(AppResponse.builder().error(false).message(ConstantValues.SUCCESS)
				.data(feedService.addFeed(image, feedsDto)).build(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<AppResponse> viewFeed(@PathVariable Integer id) {
		return new ResponseEntity<>(AppResponse.builder().error(false).message(ConstantValues.SUCCESS)
				.data(feedService.viewFeed(id)).build(), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<AppResponse> deleteFeed(@PathVariable Integer id) {
		return new ResponseEntity<>(AppResponse.builder().error(false).message(ConstantValues.SUCCESS)
				.data(feedService.deleteFeed(id)).build(), HttpStatus.OK);
	}

}
