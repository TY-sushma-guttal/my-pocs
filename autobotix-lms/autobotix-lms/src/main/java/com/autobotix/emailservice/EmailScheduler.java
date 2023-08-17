package com.autobotix.emailservice;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.autobotix.entity.Feeds;
import com.autobotix.entity.PromotionalEmail;
import com.autobotix.repository.FeedRepository;
import com.autobotix.repository.PromotionalEmailRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class EmailScheduler {

	private final EmailService emailService;
	private final FeedRepository feedRepository;
	private final PromotionalEmailRepository promotionalEmailRepository;


	@Scheduled(cron = "0 0 11 * * *")
	public void emailScheduling() {

		List<PromotionalEmail> emails = promotionalEmailRepository.findByEmailNotNull();
		Object[] collect = emails.stream().map(PromotionalEmail::getEmail).filter(Objects::nonNull)
				.collect(Collectors.toList()).toArray();

		List<Feeds> latestFeeds = feedRepository.findByIsSentFalse();
		if (latestFeeds != null) {
			for (Feeds feeds : latestFeeds) {
				emailService.sendMultipleMail("Latest Update", feeds.getDescription(), collect);
				feeds.setIsSent(true);
				feedRepository.save(feeds);
			}
		}

	}
}
