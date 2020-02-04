package com.business.unknow.services.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.business.unknow.model.error.InvoiceCommonException;

@Component
public class AnimalDispatcher {
	
	@Autowired
	private ApplicationContext context;
	
	
	public Animal getAnimal(String animal) throws InvoiceCommonException {
		if(animal.equals("gato")) {
			return context.getBean(Gato.class);
		}else if(animal.equals("perro")) {
			return context.getBean(Perro.class);
		}else if(animal.equals("elefante")) {
			return context.getBean(Elefante.class);
		}else if(animal.equals("venado")) {
			return context.getBean(Venado.class);
		}else {
			throw new InvoiceCommonException("culo");
		}
	}
	
}
