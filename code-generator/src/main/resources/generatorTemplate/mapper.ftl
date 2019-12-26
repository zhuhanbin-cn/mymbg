package ${serverPackage}.dao;

import ${serverPackage}.entity.${modelNameUpperCamel};
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;
/**
* @package:${serverPackage}.controller
* @author: ${author}
* @create: ${date}
**/
@Repository
@org.apache.ibatis.annotations.Mapper
public interface ${modelNameUpperCamel}Mapper extends Mapper<${modelNameUpperCamel}> {
}
