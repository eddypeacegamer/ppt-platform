package com.business.unknow.services.entities.catalogs;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "STATUS_PAGO")
public class StatusPago implements Serializable {

	private static final long serialVersionUID = 8104010680834757813L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_STATUS_PAGO")
	private Integer id;
	
	@Column(name = "VALUE")
	private String value;
}
