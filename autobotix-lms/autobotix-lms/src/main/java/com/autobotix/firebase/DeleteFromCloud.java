package com.autobotix.firebase;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.autobotix.exceptions.FireBaseException;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DeleteFromCloud {

	@Value("${firebase.bucket}")
	private String bucketName;

	public boolean deleteFile(String path) {

		try {
			Storage storage = StorageOptions.newBuilder().setProjectId("autobotix-trial").build().getService();
			BlobId blobId = BlobId.of(bucketName, path);
			Blob blob = storage.get(blobId);
			return blob.delete();

		} catch (Exception e) {
			e.printStackTrace();
			throw new FireBaseException("Something Wrong With FireBase Connectivity");

		}

	}

}
