package com.autobotix.service.admin;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.autobotix.dto.EventsDto;
import com.autobotix.entity.Events;
import com.autobotix.repository.EventRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {
	
	private final ModelMapper modelMapper;
	private final EventRepository eventRepository;

	@Override
	public String addEvent(EventsDto eventsDto) {
		Events events = new Events();
		modelMapper.map(eventsDto, events);
		eventRepository.save(events);
		return "save successfull";
	}

}
