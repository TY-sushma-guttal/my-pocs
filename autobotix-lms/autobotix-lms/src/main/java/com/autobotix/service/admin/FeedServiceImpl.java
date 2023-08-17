package com.autobotix.service.admin;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.autobotix.dto.FeedResponse;
import com.autobotix.dto.FeedsDto;
import com.autobotix.entity.Feeds;
import com.autobotix.exceptions.EmptyFileException;
import com.autobotix.exceptions.FeedException;
import com.autobotix.exceptions.FeedNotFoundException;
import com.autobotix.firebase.UploadToCloud;
import com.autobotix.repository.FeedRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FeedServiceImpl implements FeedService {
	
	private final ModelMapper modelMapper;
	private final UploadToCloud toCloud;
	private final FeedRepository feedRepository;
	
	@Override
	public String addFeed(MultipartFile image, FeedsDto feedsDto) {
		try {
			String uploadImage = null;
			if (image.isEmpty()) {
				throw new EmptyFileException("No File Found");
			}
			uploadImage = toCloud.uploadImage(image);
			Feeds feeds = new Feeds();
			BeanUtils.copyProperties(feedsDto, feeds);
			feeds.setImageLink(uploadImage);
			feedRepository.save(feeds);
			return "feed added";
		} catch (FeedException e) {
			e.printStackTrace();
			throw e;
		}
	}

	@Override
	public FeedResponse viewFeed(Integer id) {
		Optional<Feeds> findById = feedRepository.findById(id);
		if (findById.isPresent()) {
			FeedResponse feedDto = new FeedResponse();
			modelMapper.map(findById.get(), feedDto);
			return feedDto;
		}
		throw new FeedNotFoundException("No Feed Found");
	}

	@Override
	public String deleteFeed(Integer id) {
		try {
			Optional<Feeds> findById = feedRepository.findById(id);
			if (findById.isPresent()) {
				feedRepository.delete(findById.get());
				return "Feed deleted";
			}
			throw new FeedNotFoundException("No Feed Found");
		} catch (Exception e) {
			e.printStackTrace();
			throw new FeedException("Something Wrong In Feed");
		}
	}

}
