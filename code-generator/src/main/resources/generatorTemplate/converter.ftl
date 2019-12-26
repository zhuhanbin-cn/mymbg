package ${serverPackage}.converter;

import ${serverPackage}.entity.${modelNameUpperCamel};
import ${clientPackage}.vo.Req${modelNameUpperCamel}VO;
import ${clientPackage}.vo.Res${modelNameUpperCamel}VO;
import ${clientPackage}.vo.ShopDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import java.util.List;

 
/**
* @package:${serverPackage}.controller
* @author: ${author}
* @create: ${date}
**/
@Mapper(componentModel = "spring")
public interface ${modelNameUpperCamel}Converter {
	${modelNameUpperCamel}Converter INSTANCE = Mappers.getMapper(${modelNameUpperCamel}Converter.class);
	
	 /**
     *
     * @param ${modelNameLowerCamel}
     * @return
     */
	Res${modelNameUpperCamel}VO ${modelNameLowerCamel}2Res${modelNameUpperCamel}VO(${modelNameUpperCamel} ${modelNameLowerCamel});
    
	
	 /**
     *
     * @param ${modelNameLowerCamel}
     * @return
     */
	List<Res${modelNameUpperCamel}VO> ${modelNameLowerCamel}s2Res${modelNameUpperCamel}VOs(List<${modelNameUpperCamel}> ${modelNameLowerCamel});
	
	
	 /**
     *
     * @param ${modelNameUpperCamel}DTO
     * @return
     */
    ${modelNameUpperCamel} ${modelNameLowerCamel}DTO2Bean(${modelNameUpperCamel}DTO ${modelNameLowerCamel}DTO);
    
	/**
     *
     * @param req${modelNameUpperCamel}VO
     * @return
     */
    ${modelNameUpperCamel}DTO req${modelNameUpperCamel}VO2${modelNameUpperCamel}DTO(Req${modelNameUpperCamel}VO req${modelNameUpperCamel}VO);
	
	
	 /**
     *
     * @param req${modelNameUpperCamel}VOs
     * @return
     */
    List<${modelNameUpperCamel}DTO> req${modelNameUpperCamel}VOs2${modelNameUpperCamel}DTOs (List<Req${modelNameUpperCamel}VO> req${modelNameUpperCamel}VOs);
	
	
}