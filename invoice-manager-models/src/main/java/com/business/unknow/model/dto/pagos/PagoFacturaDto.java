/**
 * 
 */
package com.business.unknow.model.dto.pagos;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.business.unknow.Constants;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * @author ralfdemoledor
 *
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class PagoFacturaDto implements Serializable {

	private static final long serialVersionUID = -3623786015284346953L;
	private Integer id;
	private Integer idCfdi;
	private String folio;
	private BigDecimal monto;
	private BigDecimal totalFactura;
	private String acredor;
	private String deudor;
	private String metodoPago;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Constants.JSON_DATE_FORMAT)
	private Date fechaCreacion;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = Constants.JSON_DATE_FORMAT)
	private Date fechaActualizacion;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getIdCfdi() {
		return idCfdi;
	}

	public void setIdCfdi(Integer idCfdi) {
		this.idCfdi = idCfdi;
	}

	public String getFolio() {
		return folio;
	}

	public void setFolio(String folio) {
		this.folio = folio;
	}

	public BigDecimal getMonto() {
		return monto;
	}

	public void setMonto(BigDecimal monto) {
		this.monto = monto;
	}

	public BigDecimal getTotalFactura() {
		return totalFactura;
	}

	public void setTotalFactura(BigDecimal totalFactura) {
		this.totalFactura = totalFactura;
	}

	public String getAcredor() {
		return acredor;
	}

	public void setAcredor(String acredor) {
		this.acredor = acredor;
	}

	public String getDeudor() {
		return deudor;
	}

	public void setDeudor(String deudor) {
		this.deudor = deudor;
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

	public String getMetodoPago() {
		return metodoPago;
	}

	public void setMetodoPago(String metodoPago) {
		this.metodoPago = metodoPago;
	}

	@Override
	public String toString() {
		return "PagoFacturaDto [id=" + id + ", idCfdi=" + idCfdi + ", folio=" + folio + ", monto=" + monto
				+ ", totalFactura=" + totalFactura + ", acredor=" + acredor + ", deudor=" + deudor + ", metodoPago="
				+ metodoPago + ", fechaCreacion=" + fechaCreacion + ", fechaActualizacion=" + fechaActualizacion + "]";
	}
}
