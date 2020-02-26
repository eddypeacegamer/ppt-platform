package com.business.unknow.commons.builder;

import java.util.ArrayList;
import java.util.List;

import com.business.unknow.model.config.EmailConfig;
import com.business.unknow.model.config.FileConfig;

public class EmailConfigBuilder extends AbstractBuilder<EmailConfig> {

	public EmailConfigBuilder() {
		super(new EmailConfig());
	}

	public EmailConfigBuilder setAsunto(String asunto) {
		instance.setAsunto(asunto);
		return this;
	}

	public EmailConfigBuilder setCuerpo(String cuerpo) {
		instance.setCuerpo(cuerpo);
		return this;
	}

	public EmailConfigBuilder setEmisor(String emisor) {
		instance.setEmisor(emisor);
		return this;
	}

	public EmailConfigBuilder setPwEmisor(String pwEmisor) {
		instance.setPwEmisor(pwEmisor);
		return this;
	}

	public EmailConfigBuilder setReceptor(List<String> receptor) {
		instance.setReceptor(receptor);
		return this;
	}
	
	public EmailConfigBuilder addReceptor(String receptor) {
		if(instance.getReceptor()==null) {
			instance.setReceptor(new ArrayList<>());
		}
		instance.getReceptor().add(receptor);
		return this;
	}

	public EmailConfigBuilder addArchivo(FileConfig fileConfig) {
		if (instance.getArchivos() == null) {
			instance.setArchivos(new ArrayList<>());
		}
		instance.getArchivos().add(fileConfig);
		return this;
	}

	public EmailConfigBuilder setArchivos(List<FileConfig> archivos) {
		instance.setArchivos(archivos);
		return this;
	}
}