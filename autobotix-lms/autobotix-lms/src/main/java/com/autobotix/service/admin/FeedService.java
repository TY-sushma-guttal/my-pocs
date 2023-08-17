package com.autobotix.service.admin;

import org.springframework.web.multipart.MultipartFile;

import com.autobotix.dto.FeedResponse;
import com.autobotix.dto.FeedsDto;

public interface FeedService {

	String addFeed(MultipartFile image, FeedsDto feedsDto);

	FeedResponse viewFeed(Integer id);

	String deleteFeed(Integer id);
}
