package com.business.unknow.services.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

@Entity
@Table(name = "EMPRESAS")
public class Empresa implements Serializable {

	private static final long serialVersionUID = 8251482734350985993L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_EMPRESA")
	private Integer id;

	@Column(name = "REGIMEN_FISCAL")
	private String regimenFiscal;

	@Column(name = "REFERENCIA")
	private String referencia;

	@Column(name = "web")
	private String web;

	@Column(name = "CONTACTO_ADMIN")
	private String contactoAdmin;

	@Basic(optional = false)
	@Column(name = "SUCURSAL")
	private String sucursal;

	@Basic(optional = false)
	@Column(name = "LUGAR_EXPEDICION")
	private String lugarExpedicion;

	@Column(name = "LOGOTIPO")
	private String logotipo;

	private String llavePrivada;

	@Column(name = "CERTIFICADO")
	private String certificado;

	@Column(name = "PW")
	private String pw;

	@Column(name = "ENCABEZADO")
	private String encabezado;

	@Column(name = "PIE_DE_PAGINA")
	private String piePagina;

	@Column(name = "ACTIVO")
	private Boolean activo;

	@Column(name = "LINEA")
	private String tipo;
	
	@Column(name = "GIRO_ID")
	private Integer giro;

	@Temporal(TemporalType.DATE)
	@CreatedDate
	@Column(name = "FECHA_CREACION")
	private Date fechaCreacion;

	@Temporal(TemporalType.DATE)
	@LastModifiedDate
	@Column(name = "FECHA_ACTUALIZACION")
	private Date fechaActualizacion;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "RFC", referencedColumnName = "RFC")
	private Contribuyente informacionFiscal;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRegimenFiscal() {
		return regimenFiscal;
	}

	public void setRegimenFiscal(String regimenFiscal) {
		this.regimenFiscal = regimenFiscal;
	}

	public String getReferencia() {
		return referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	public String getWeb() {
		return web;
	}

	public void setWeb(String web) {
		this.web = web;
	}

	public String getContactoAdmin() {
		return contactoAdmin;
	}

	public void setContactoAdmin(String contactoAdmin) {
		this.contactoAdmin = contactoAdmin;
	}

	public String getSucursal() {
		return sucursal;
	}

	public void setSucursal(String sucursal) {
		this.sucursal = sucursal;
	}

	public String getLugarExpedicion() {
		return lugarExpedicion;
	}

	public void setLugarExpedicion(String lugarExpedicion) {
		this.lugarExpedicion = lugarExpedicion;
	}

	public String getLogotipo() {
		return logotipo;
	}

	public void setLogotipo(String logotipo) {
		this.logotipo = logotipo;
	}

	public String getLlavePrivada() {
		return llavePrivada;
	}

	public void setLlavePrivada(String llavePrivada) {
		this.llavePrivada = llavePrivada;
	}

	public String getCertificado() {
		return certificado;
	}

	public void setCertificado(String certificado) {
		this.certificado = certificado;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getEncabezado() {
		return encabezado;
	}

	public void setEncabezado(String encabezado) {
		this.encabezado = encabezado;
	}

	public String getPiePagina() {
		return piePagina;
	}

	public void setPiePagina(String piePagina) {
		this.piePagina = piePagina;
	}

	public Boolean getActivo() {
		return activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Integer getGiro() {
		return giro;
	}

	public void setGiro(Integer giro) {
		this.giro = giro;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Date getFechaActualizacion() {
		return fechaActualizacion;
	}

	public void setFechaActualizacion(Date fechaActualizacion) {
		this.fechaActualizacion = fechaActualizacion;
	}

	public Contribuyente getInformacionFiscal() {
		return informacionFiscal;
	}

	public void setInformacionFiscal(Contribuyente informacionFiscal) {
		this.informacionFiscal = informacionFiscal;
	}

	@Override
	public String toString() {
		return "Empresa [id=" + id + ", regimenFiscal=" + regimenFiscal + ", referencia=" + referencia + ", web=" + web
				+ ", contactoAdmin=" + contactoAdmin + ", sucursal=" + sucursal + ", lugarExpedicion=" + lugarExpedicion
				+ ", logotipo=" + logotipo + ", llavePrivada=" + llavePrivada + ", certificado=" + certificado + ", pw="
				+ pw + ", encabezado=" + encabezado + ", piePagina=" + piePagina + ", activo=" + activo + ", tipo="
				+ tipo + ", fechaCreacion=" + fechaCreacion + ", fechaActualizacion=" + fechaActualizacion
				+ ", informacionFiscal=" + informacionFiscal + "]";
	}
}
