/**
 * 
 */
package com.business.unknow.services.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.business.unknow.model.files.FacturaFileDto;
import com.business.unknow.model.files.ResourceFileDto;
import com.business.unknow.services.entities.files.FacturaFile;
import com.business.unknow.services.entities.files.ResourceFile;

/**
 * @author hha0009
 *
 */
@Mapper
public interface FilesMapper {
	

	@Mapping(target = "data",expression = "java( new String(file.data))") 
	public ResourceFileDto getResourceFileDtoFromEntity(ResourceFile file);
		
	@Mapping(target = "data",expression = "java( file.data.getBytes())")
	public ResourceFile getResourceFileFromDto(ResourceFileDto file);
	
	@Mapping(target = "data",expression = "java( new String(file.data))")
	public FacturaFileDto getFacturaFileDtoFromEntity(FacturaFile file);
	
	@Mapping(target = "data",expression = "java( file.data.getBytes())")
	public FacturaFile getFacturaFileFromDto(FacturaFileDto file);

}
