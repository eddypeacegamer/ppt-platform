package com.business.unknow.model.config;

import java.util.List;

public class EmailConfig {

	private List<String> receptor;
	private String emisor;
	private String dominio;
	private String asunto;
	private String cuerpo;
	private String pwEmisor;
	private String port;
	private List<FileConfig> archivos;

	public List<String> getReceptor() {
		return receptor;
	}

	public void setReceptor(List<String> receptor) {
		this.receptor = receptor;
	}

	public String getEmisor() {
		return emisor;
	}

	public void setEmisor(String emisor) {
		this.emisor = emisor;
	}

	public String getAsunto() {
		return asunto;
	}

	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}

	public String getCuerpo() {
		return cuerpo;
	}

	public void setCuerpo(String cuerpo) {
		this.cuerpo = cuerpo;
	}

	public String getPwEmisor() {
		return pwEmisor;
	}

	public void setPwEmisor(String pwEmisor) {
		this.pwEmisor = pwEmisor;
	}

	public List<FileConfig> getArchivos() {
		return archivos;
	}

	public void setArchivos(List<FileConfig> archivos) {
		this.archivos = archivos;
	}

	public String getDominio() {
		return dominio;
	}

	public void setDominio(String dominio) {
		this.dominio = dominio;
	}
	
	

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	@Override
	public String toString() {
		return "EmailConfig [receptor=" + receptor + ", emisor=" + emisor + ", dominio=" + dominio + ", asunto="
				+ asunto + ", cuerpo=" + cuerpo + ", pwEmisor=" + pwEmisor + ", port=" + port + ", archivos=" + archivos
				+ "]";
	}

}
