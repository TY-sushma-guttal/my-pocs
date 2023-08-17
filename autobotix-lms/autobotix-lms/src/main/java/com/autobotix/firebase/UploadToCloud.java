package com.autobotix.firebase;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.autobotix.exceptions.FileNotFoundException;
import com.autobotix.exceptions.FireBaseException;
import com.autobotix.exceptions.StorageException;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;

@Service
public class UploadToCloud {

	@Value("${firebase.bucket}")
	private String bucketName;

	public String uploadImage(MultipartFile image) {
		try {
			String fileName = generateFileName(image);
			BlobInfo blobInfo = BlobInfo.newBuilder(bucketName, fileName).build();
			byte[] bytes;
			Storage storage = getStorage();
			if (storage != null) {
				bytes = image.getBytes();
				Blob create = storage.create(blobInfo, bytes);
				System.err.println(create);
				return String.format("https://storage.googleapis.com/%s/%s", bucketName, fileName);
			}
			throw new StorageException("Storage Not Found! Check The Cloud Connection");
		} catch (IOException e) {
			e.printStackTrace();
			throw new FireBaseException("Something Wrong With FireBase Connectivity");
		}

	}

	private String generateFileName(MultipartFile file) {
		String originalFilename = file.getOriginalFilename();
		if (originalFilename != null) {
			return UUID.randomUUID().toString() + "." + originalFilename.split("\\.")[1];
		}
		throw new FileNotFoundException("No File Found");
	}

	private Storage getStorage() {
		try {
			ClassLoader classLoader = getClass().getClassLoader();
			File file = new File(Objects.requireNonNull(classLoader.getResource("serviceKey.json")).getFile());
			GoogleCredentials credentials = GoogleCredentials.fromStream(new FileInputStream(file));

			return StorageOptions.newBuilder().setCredentials(credentials).build().getService();
		} catch (

		IOException e) {
			e.printStackTrace();
			return null;
		}
	}

}
