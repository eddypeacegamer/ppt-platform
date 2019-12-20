/**
 * 
 */
package com.business.unknow.services.services;

import java.util.Date;
import java.util.Optional;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.business.unknow.model.PagoDto;
import com.business.unknow.services.entities.Pago;
import com.business.unknow.services.mapper.PagoMapper;
import com.business.unknow.services.repositories.facturas.PagoRepository;

/**
 *@author ralfdemoledor
 *
 */
@Service
public class PagoService {
	
	@Autowired
	private PagoRepository repository;
	
	@Autowired
	private PagoMapper mapper;
	
	private static final Logger log = LoggerFactory.getLogger(PagoService.class);
	
	public Page<PagoDto> getPaginatedPayments(Optional<String> folio,String formaPago,String status,String banco,Date since, Date to,  int page,int size){
		Date start = (since == null) ? new DateTime().minusYears(1).toDate() : since;
		Date end = (to == null) ? new Date() : to;
		Page<Pago> result;
		if (folio.isPresent()) {
			log.info("Searching PaymentsByFolio {}", folio.get());
			result = repository.findByFolioIgnoreCaseContaining(folio.get(), PageRequest.of(0, 10));
		}else {
			log.info("Search payments by status {}, formapago {}, banco {} and start {} y end {}", status,formaPago,banco, start, end);
			result = repository.findPagosByFilterParams(String.format("%%%s%%", status), String.format("%%%s%%", formaPago), String.format("%%%s%%", banco), start, end, PageRequest.of(page, size));
		}
		
		return new PageImpl<>(mapper.getPagosDtoFromEntities(result.getContent()), result.getPageable(),
				result.getTotalElements());
	}

}
