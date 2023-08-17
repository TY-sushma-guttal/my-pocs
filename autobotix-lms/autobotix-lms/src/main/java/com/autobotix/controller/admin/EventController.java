package com.autobotix.controller.admin;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.autobotix.dto.EventsDto;
import com.autobotix.response.AppResponse;
import com.autobotix.service.admin.EventService;
import com.autobotix.util.ConstantValues;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/event")
public class EventController {

	private final EventService eventService;

	@PostMapping("/")
	public ResponseEntity<AppResponse> addEvent(@RequestBody EventsDto eventsDto) {
		return new ResponseEntity<>(AppResponse.builder().error(false).message(ConstantValues.SUCCESS)
				.data(eventService.addEvent(eventsDto)).build(), HttpStatus.OK);
	}

}
