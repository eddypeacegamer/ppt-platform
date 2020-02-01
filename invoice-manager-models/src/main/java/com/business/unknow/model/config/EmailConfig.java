package com.business.unknow.model.config;

import java.util.List;

public class EmailConfig {

	private String receptor;
	private String emisor;
	private String asunto;
	private String cuerpo;
	private String pwEmisor;
	private List<FileConfig> archivos;

	public String getReceptor() {
		return receptor;
	}

	public void setReceptor(String receptor) {
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

	@Override
	public String toString() {
		return "EmailConfig [receptor=" + receptor + ", emisor=" + emisor + ", asunto=" + asunto + ", cuerpo=" + cuerpo
				+ ", pwEmisor=" + pwEmisor + ", archivos=" + archivos + "]";
	}

}
