package com.business.unknow.enums;

public enum TipoArchivoEnum {

	XML(1, "XML", ".xml", "text/plain"),
	QR(2, "QR", ".png", "N/A"),
	PDF(3, "PDF", ".pdf", "application/pdf"),
	TXT(4, "TXT", ".txt", "text/plain");

	private Integer valor;
	private String descripcion;
	private String format;
	private String byteArrayData;

	private TipoArchivoEnum(Integer valor, String descripcion, String format, String byteArrayData) {
		this.valor = valor;
		this.descripcion = descripcion;
		this.format = format;
		this.byteArrayData = byteArrayData;
	}

	public Integer getValor() {
		return valor;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public String getFormat() {
		return format;
	}

	public String getByteArrayData() {
		return byteArrayData;
	}
}
