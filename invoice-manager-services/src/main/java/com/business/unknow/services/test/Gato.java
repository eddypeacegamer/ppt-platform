package com.business.unknow.services.test;

import org.springframework.stereotype.Service;

@Service("Gato")
public class Gato extends Animal {

	public void imprime() {
		System.out.println("Gato");
	}
}
