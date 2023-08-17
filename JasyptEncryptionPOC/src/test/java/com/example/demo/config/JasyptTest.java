package com.example.demo.config;


import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.junit.jupiter.api.Test;

class JasyptTest {

	
	@Test
	public void jasyptEncryptTest() {
		PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
		SimpleStringPBEConfig config = new SimpleStringPBEConfig();
		config.setPassword("Techno@_Elevate"); // encryptor's private key
		config.setAlgorithm("PBEWithMD5AndTripleDES");
		config.setKeyObtentionIterations("1000");
		config.setPoolSize("1");
		config.setProviderName("SunJCE");
		config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator");
		config.setStringOutputType("base64");
		encryptor.setConfig(config);
		
		String rawtext = "Database@123";
		String encryptedText = encryptor.encrypt(rawtext);
		System.err.println(encryptor.decrypt("Ou5yrhCdagDGI+AkKKZlFw=="));
	}

}
