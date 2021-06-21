package com.bank.app;

import org.springframework.stereotype.Service;

import com.bank.app.core.EngageFatalException;

@Service
public class TestService {
	
	public void name() {
//		try {
//			System.out.println(10/0);	
//		} catch (Exception e) {
//			// TODO: handle exception
//			throw new EngageFatalException("custom exception throw");
//		}

		System.out.println(10/0);	
	}

}
