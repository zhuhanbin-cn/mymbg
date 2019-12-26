package ${serverPackage}.service;
import ${clientPackage}.vo.Res${modelNameUpperCamel}VO;
import ${clientPackage}.vo.Req${modelNameUpperCamel}VO;
import ${clientPackage}.vo.${modelNameUpperCamel}DTO;
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
	 * @param req${modelNameUpperCamel}VO
	 * @return ResponseResult<List<Res${modelNameUpperCamel}VO>>
	 * @author: ${author}
	 * @create: ${date}
	 */
	ResponseResult<List<Res${modelNameUpperCamel}VO>> getList(Req${modelNameUpperCamel}VO req${modelNameUpperCamel}VO);
	/**
	 * 分页${comment}列表
	 * @param ${modelNameLowerCamel}DTO
	 * @return
	 */
	ResponseResult<Res${modelNameUpperCamel}VO > getListPageSize(${modelNameUpperCamel}DTO ${modelNameLowerCamel}DTO);
	/**
	 * 获取单个${comment}详情
	 * @param  ${modelNameUpperCamel}DTO
	 * @return	ResponseResult<Res${modelNameUpperCamel}VO>
	 * @author: ${author}
	 * @create: ${date}
	 */
	ResponseResult<Res${modelNameUpperCamel}VO> getDetail(${modelNameUpperCamel}DTO ${modelNameUpperCamel}DTO);
	/**
	 * 新增${comment}
	 * @param ${modelNameUpperCamel}DTO
	 * @return ResponseResult<List<Res${modelNameUpperCamel}VO>>
	 * @author: ${author}
	 * @create: ${date}
	 */
	ResponseResult add(${modelNameUpperCamel}DTO ${modelNameUpperCamel}DTO);
	/**
	 * 删除${comment}
	 * @param ${modelNameUpperCamel}DTO
	 * @return ResponseResult<Res${modelNameUpperCamel}VO>
	 * @author: ${author}
	 * @create: ${date}
	 */
	ResponseResult delete(${modelNameUpperCamel}DTO ${modelNameUpperCamel}DTO);
	/**
	 * 更新${comment}
	 * @param ${modelNameUpperCamel}DTO
	 * @return ResponseResult<Res${modelNameUpperCamel}VO>
	 * @author: ${author}
	 * @create: ${date}
	 */
	ResponseResult update(${modelNameUpperCamel}DTO ${modelNameUpperCamel}DTO);
	
	
}