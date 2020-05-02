/**
 * 
 */
package com.business.unknow.services.entities;

import java.io.Serializable;
import java.util.Date;

/**
 * @author hha0009
 *
 */
//@Entity
//@EntityListeners(AuditingEntityListener.class)
//@Table(name = "TRANSFERENCIAS")
@Deprecated
public class Transferencia implements Serializable {

	private static final long serialVersionUID = 1152989539957300970L;

//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	@Column(name = "ID_TRANSFER")
	private Integer id;

//	@Column(name = "BANCO_RETIRO")
	private String bancoRetiro;

//	@Column(name = "RFC_RETIRO")
	private String rfcRetiro;

//	@Column(name = "CUENTA_RETIRO")
	private String cuentaRetiro;

//	@Column(name = "LINEA_RETIRO")
	private String lineaRetiro;

//	@Column(name = "BANCO_DEPOSITO")
	private String bancoDeposito;

//	@Column(name = "RFC_DEPOSITO")
	private String rfcDeposito;

//	@Column(name = "CUENTA_DEPOSITO")
	private String cuentaDeposito;

//	@Column(name = "LINEA_DEPOSITO")
	private String lineaDeposito;

//	@Column(name = "FOLIO")
	private String folio;

//	@Column(name = "IMPORTE")
	private Double importe;

//	@Temporal(TemporalType.TIMESTAMP)
//	@CreatedDate
//	@Column(name = "FECHA_CREACION")
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
		return "Transferencia [id=" + id + ", bancoRetiro=" + bancoRetiro + ", rfcRetiro=" + rfcRetiro
				+ ", cuentaRetiro=" + cuentaRetiro + ", lineaRetiro=" + lineaRetiro + ", bancoDeposito=" + bancoDeposito
				+ ", rfcDeposito=" + rfcDeposito + ", cuentaDeposito=" + cuentaDeposito + ", lineaDeposito="
				+ lineaDeposito + ", folio=" + folio + ", importe=" + importe + ", fechaCreacion=" + fechaCreacion
				+ "]";
	}

}
