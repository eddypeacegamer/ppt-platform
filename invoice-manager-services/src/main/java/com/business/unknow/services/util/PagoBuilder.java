package com.business.unknow.services.util;

import java.math.BigDecimal;
import java.util.Date;

import com.business.unknow.commons.builder.AbstractBuilder;
import com.business.unknow.model.dto.pagos.PagoDto;

public class PagoBuilder extends AbstractBuilder<PagoDto> {

	public PagoBuilder() {
		super(new PagoDto());
	}
	
	public PagoBuilder(PagoDto previoudPago) {
		super(previoudPago);
	}
	
	public PagoBuilder setId(Integer id) {
		instance.setId(id);
	return this; }
	public PagoBuilder setMoneda(String moneda) {
		instance.setMoneda(moneda);
	return this; }
	public PagoBuilder setBanco(String banco) {
		instance.setBanco(banco);
	return this; }
	public PagoBuilder setCuenta(String cuenta) {
		instance.setCuenta(cuenta);
	return this; }
	public PagoBuilder setTipoDeCambio(BigDecimal tipoDeCambio) {
		instance.setTipoDeCambio(tipoDeCambio);
	return this; }
	public PagoBuilder setFormaPago(String formaPago) {
		instance.setFormaPago(formaPago);
	return this; }
	public PagoBuilder setMonto(BigDecimal monto) {
		instance.setMonto(monto);
	return this; }
	public PagoBuilder setStatusPago(String statusPago) {
		instance.setStatusPago(statusPago);
	return this; }
	public PagoBuilder setComentarioPago(String comentarioPago) {
		instance.setComentarioPago(comentarioPago);
	return this; }
	public PagoBuilder setSolicitante(String solicitante) {
		instance.setSolicitante(solicitante);
	return this; }
	public PagoBuilder setRevision1(Boolean revision1) {
		instance.setRevision1(revision1);
	return this; }
	public PagoBuilder setRevision2(Boolean revision2) {
		instance.setRevision2(revision2);
	return this; }
	public PagoBuilder setRevisor1(String revisor1) {
		instance.setRevisor1(revisor1);
	return this; }
	public PagoBuilder setRevisor2(String revisor2) {
		instance.setRevisor2(revisor2);
	return this; }
	public PagoBuilder setFechaPago(Date fechaPago) {
		instance.setFechaPago(fechaPago);
	return this; }
	

}
