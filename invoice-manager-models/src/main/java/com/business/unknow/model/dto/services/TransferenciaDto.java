package com.business.unknow.model.dto.services;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class TransferenciaDto implements Serializable {

	private static final long serialVersionUID = 39453424147826945L;
	private Integer id;
	private String bancoRetiro;
	private String rfcRetiro;
	private String cuentaRetiro;
	private String lineaRetiro;
	private String bancoDeposito;
	private String rfcDeposito;
	private String cuentaDeposito;
	private String lineaDeposito;
	private String folio;
	private Double importe;
	private Date fechaCreacion;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getBancoRetiro() {
		return bancoRetiro;
	}

	public void setBancoRetiro(String bancoRetiro) {
		this.bancoRetiro = bancoRetiro;
	}

	public String getRfcRetiro() {
		return rfcRetiro;
	}

	public void setRfcRetiro(String rfcRetiro) {
		this.rfcRetiro = rfcRetiro;
	}

	public String getCuentaRetiro() {
		return cuentaRetiro;
	}

	public void setCuentaRetiro(String cuentaRetiro) {
		this.cuentaRetiro = cuentaRetiro;
	}

	public String getLineaRetiro() {
		return lineaRetiro;
	}

	public void setLineaRetiro(String lineaRetiro) {
		this.lineaRetiro = lineaRetiro;
	}

	public String getBancoDeposito() {
		return bancoDeposito;
	}

	public void setBancoDeposito(String bancoDeposito) {
		this.bancoDeposito = bancoDeposito;
	}

	public String getRfcDeposito() {
		return rfcDeposito;
	}

	public void setRfcDeposito(String rfcDeposito) {
		this.rfcDeposito = rfcDeposito;
	}

	public String getCuentaDeposito() {
		return cuentaDeposito;
	}

	public void setCuentaDeposito(String cuentaDeposito) {
		this.cuentaDeposito = cuentaDeposito;
	}

	public String getLineaDeposito() {
		return lineaDeposito;
	}

	public void setLineaDeposito(String lineaDeposito) {
		this.lineaDeposito = lineaDeposito;
	}

	public Double getImporte() {
		return importe;
	}

	public void setImporte(Double importe) {
		this.importe = importe;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public String getFolio() {
		return folio;
	}

	public void setFolio(String folio) {
		this.folio = folio;
	}

	@Override
	public String toString() {
		return "TransferenciaDto [id=" + id + ", bancoRetiro=" + bancoRetiro + ", rfcRetiro=" + rfcRetiro
				+ ", cuentaRetiro=" + cuentaRetiro + ", lineaRetiro=" + lineaRetiro + ", bancoDeposito=" + bancoDeposito
				+ ", rfcDeposito=" + rfcDeposito + ", cuentaDeposito=" + cuentaDeposito + ", lineaDeposito="
				+ lineaDeposito + ", folio=" + folio + ", importe=" + importe + ", fechaCreacion=" + fechaCreacion
				+ "]";
	}

}
