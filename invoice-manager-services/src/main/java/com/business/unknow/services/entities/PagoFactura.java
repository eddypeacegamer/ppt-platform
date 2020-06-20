/**
 * 
 */
package com.business.unknow.services.entities;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * @author ralfdemoledor
 *
 */
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "PAGO_FACTURAS")
public class PagoFactura {

}
