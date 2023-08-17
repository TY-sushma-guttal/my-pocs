package com.autobotix.util;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Service;

import com.autobotix.exceptions.OtpException;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

@Service
public class OTPGenerator {
	private static final int EXPIRE_TIME_IN_MINUTES = 1;
	private Random random = new Random();
	private static LoadingCache<String, Integer> otpCache;

	static {
		otpCache = CacheBuilder.newBuilder().expireAfterWrite(EXPIRE_TIME_IN_MINUTES, TimeUnit.MINUTES)
				.build(new CacheLoader<String, Integer>() {
					public Integer load(String key) {
						return 0;
					}
				});
	}

	public Integer generateOTP(String email) {
		Integer cachedOTP = otpCache.getIfPresent(email);
		if (cachedOTP != null) {
			throw new OtpException("Otp already generated for " + email);
		}
		Integer otp = 100000 + random.nextInt(900000);
		otpCache.put(email, otp);
		return otp;
	}

	public Boolean verifyOTP(String email, int otp) {
		Integer cachedOTP = otpCache.getIfPresent(email);

		if (cachedOTP == null || cachedOTP != otp) {
			throw new OtpException("Invalid Otp");
		}
		return true;
	}

	public void clearOTP(String email) {
		otpCache.invalidate(email);

	}
}
