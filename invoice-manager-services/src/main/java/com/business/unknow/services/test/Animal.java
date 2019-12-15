package com.business.unknow.services.test;

import org.springframework.stereotype.Service;

@Service("Animal")
public abstract class Animal {

	public void imprime() {
		System.out.println("Animal");
	}
}
