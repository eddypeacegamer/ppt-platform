package com.business.unknow.services.test;

import org.springframework.stereotype.Service;

@Service("Elefante")
public class Elefante extends Animal{
	public void imprime() {
		System.out.println("Elefante");
	}
}
