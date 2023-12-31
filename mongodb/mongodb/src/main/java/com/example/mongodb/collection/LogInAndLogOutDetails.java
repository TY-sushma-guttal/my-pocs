package com.example.mongodb.collection;

import java.time.LocalTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LogInAndLogOutDetails {

	private LocalTime logInTime;

	private LocalTime logOutTime;

}
