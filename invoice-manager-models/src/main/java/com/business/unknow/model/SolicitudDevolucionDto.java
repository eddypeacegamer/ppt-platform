/**
 * 
 */
package com.business.unknow.model;

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
	public List<DevolucionDto> getDevoluciones() {
		return devoluciones;
	}
	public void setDevoluciones(List<DevolucionDto> devoluciones) {
		this.devoluciones = devoluciones;
	}
	@Override
	public String toString() {
		return "SolicitudDevolucionDto [cuenta=" + cuenta + ", banco=" + banco + ", user=" + user + ", beneficiario="
				+ beneficiario + ", devoluciones=" + devoluciones + "]";
	}
}
