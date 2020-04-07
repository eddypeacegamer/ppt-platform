package com.business.unknow.model.config;

import com.business.unknow.enums.TipoArchivoEnum;

public class FileConfig {

	private TipoArchivoEnum tipòArchivo;
	private String nombre;
	private String base64Content;

	public FileConfig(TipoArchivoEnum tipòArchivo, String nombre, String base64Content) {
		super();
		this.tipòArchivo = tipòArchivo;
		this.nombre = nombre;
		this.base64Content = base64Content;
	}

	public FileConfig() {
		super();
	}

	public TipoArchivoEnum getTipòArchivo() {
		return tipòArchivo;
	}

	public void setTipòArchivo(TipoArchivoEnum tipòArchivo) {
		this.tipòArchivo = tipòArchivo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getBase64Content() {
		return base64Content;
	}

	public void setBase64Content(String base64Content) {
		this.base64Content = base64Content;
	}

	@Override
	public String toString() {
		return "FileConfig [tipòArchivo=" + tipòArchivo + ", nombre=" + nombre + ", base64Content=" + base64Content
				+ "]";
	}

}