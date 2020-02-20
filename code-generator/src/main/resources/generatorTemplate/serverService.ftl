package ${serverPackage}.service;
import ${clientPackage}.vo.Res${modelNameUpperCamel}VO;
import ${clientPackage}.vo.Req${modelNameUpperCamel}VO;
import ${clientPackage}.dto.${modelNameUpperCamel}DTO;
import com.zjbdos.springcloud.common.core.vo.ResponseResult;
import tk.mybatis.mapper.entity.Example;
import java.util.List;
/**
* @package:${serverPackage}.service
* @description:${comment}
* @author: ${author}
* @create: ${date}
**/
public interface ${modelNameUpperCamel}Service {
	/**
	 * 获取${comment}列表
	 * @return ResponseResult<List<Res${modelNameUpperCamel}VO>>
	 * @author: ${author}
	 * @create: ${date}
	 */
	ResponseResult<List<Res${modelNameUpperCamel}VO>> getList();
	/**
	 * 分页${comment}列表
	 * @param ${modelNameLowerCamel}DTO
	 * @return
	 */
	ResponseResult<Res${modelNameUpperCamel}VO > getListPageSize(${modelNameUpperCamel}DTO ${modelNameLowerCamel}DTO);
	/**
	 * 获取单个${comment}详情
	 * @param  ${modelNameLowerCamel}DTO
	 * @return	ResponseResult<Res${modelNameUpperCamel}VO>
	 * @author: ${author}
	 * @create: ${date}
	 */
	ResponseResult<Res${modelNameUpperCamel}VO> getDetail(${modelNameUpperCamel}DTO ${modelNameLowerCamel}DTO);
	/**
	 * 新增${comment}
	 * @param ${modelNameLowerCamel}DTO
	 * @return ResponseResult<List<Res${modelNameUpperCamel}VO>>
	 * @author: ${author}
	 * @create: ${date}
	 */
	ResponseResult add(${modelNameUpperCamel}DTO ${modelNameLowerCamel}DTO);
	/**
	 * 删除${comment}
	 * @param ${modelNameLowerCamel}DTO
	 * @return ResponseResult<Res${modelNameUpperCamel}VO>
	 * @author: ${author}
	 * @create: ${date}
	 */
	ResponseResult delete(${modelNameUpperCamel}DTO ${modelNameLowerCamel}DTO);
	/**
	 * 更新${comment}
	 * @param ${modelNameLowerCamel}DTO
	 * @return ResponseResult<Res${modelNameUpperCamel}VO>
	 * @author: ${author}
	 * @create: ${date}
	 */
	ResponseResult update(${modelNameUpperCamel}DTO ${modelNameLowerCamel}DTO);
	
	
}