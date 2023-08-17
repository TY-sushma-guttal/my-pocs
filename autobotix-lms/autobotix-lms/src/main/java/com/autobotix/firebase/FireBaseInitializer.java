package com.autobotix.firebase;

import java.io.InputStream;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

@Service
public class FireBaseInitializer {

	@PostConstruct
	public void getFirebaseStorage() {
		try {
			InputStream serviceAccount = this.getClass().getClassLoader().getResourceAsStream("./serviceKey.json");

			FirebaseOptions options = new FirebaseOptions.Builder()
					.setCredentials(GoogleCredentials.fromStream(serviceAccount)).build();

			FirebaseApp.initializeApp(options);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
