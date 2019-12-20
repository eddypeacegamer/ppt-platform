package com.business.unknow.services.test;

import org.springframework.stereotype.Service;

@Service("Perro")
public class Perro extends Animal{

	public void imprime() {
		System.out.println("Perro");
	}
}
