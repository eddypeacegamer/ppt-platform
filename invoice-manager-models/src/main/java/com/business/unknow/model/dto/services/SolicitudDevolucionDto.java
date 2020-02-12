/**
 * 
 */
package com.business.unknow.model.dto.services;

import java.io.Serializable;
import java.util.List;

/**
 * @author hha0009
 *
 */
public class SolicitudDevolucionDto implements Serializable{
	
	private static final long serialVersionUID = 2316155548670928919L;
	private String cuenta;
	private String banco;
	private String user;
	private String beneficiario;
	private String formaPago;
	private String moneda;
	private Double tipoCambio;
	private List<DevolucionDto> devoluciones;
	
	
	public String getCuenta() {
		return cuenta;
	}
	public void setCuenta(String cuenta) {
		this.cuenta = cuenta;
	}
	public String getBanco() {
		return banco;
	}
	public void setBanco(String banco) {
		this.banco = banco;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getBeneficiario() {
		return beneficiario;
	}
	public void setBeneficiario(String beneficiario) {
		this.beneficiario = beneficiario;
	}
	public String getFormaPago() {
		return formaPago;
	}
	public void setFormaPago(String formaPago) {
		this.formaPago = formaPago;
	}
	public String getMoneda() {
		return moneda;
	}
	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}
	public Double getTipoCambio() {
		return tipoCambio;
	}
	public void setTipoCambio(Double tipoCambio) {
		this.tipoCambio = tipoCambio;
	}
	public List<DevolucionDto> getDevoluciones() {
		return devoluciones;
	}
	public void setDevoluciones(List<DevolucionDto> devoluciones) {
		this.devoluciones = devoluciones;
	}
	@Override
	public String toString() {
		return "SolicitudDevolucionDto [cuenta=" + cuenta + ", banco=" + banco + ", user=" + user + ", beneficiario="
				+ beneficiario + ", formaPago=" + formaPago + ", moneda=" + moneda + ", tipoCambio=" + tipoCambio
				+ ", devoluciones=" + devoluciones + "]";
	}
}
