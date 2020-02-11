package ${clientPackage}.feign;

import ${clientPackage}.feign.factory.${modelNameUpperCamel}ServiceFeignFactory;
import ${clientPackage}.vo.Req${modelNameUpperCamel}VO;
import ${clientPackage}.vo.Res${modelNameUpperCamel}VO;
import com.zjbdos.springcloud.common.core.vo.ResponseResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import java.util.List;
/**
* @package:${clientPackage}.feign
* @description:
* @author: ${author}
* @create: ${date}
**/

@FeignClient(value = "${applicationName}", fallbackFactory = ${modelNameUpperCamel}ServiceFeignFactory.class)
public interface ${modelNameUpperCamel}ServiceFeign {
	/**
	 * 获取${comment}列表
	 * @param req${modelNameUpperCamel}VO
	 * @return ResponseResult
	 */
	@PostMapping("${apiurl}/getList")
	ResponseResult<List<Res${modelNameUpperCamel}VO>> getList(@RequestBody Req${modelNameUpperCamel}VO req${modelNameUpperCamel}VO);

	/**
	 * 获取${comment}详细信息
	 * @param req${modelNameUpperCamel}VO
	 * @return ResponseResult
	 */
	@PostMapping("${apiurl}/getDetail")
	ResponseResult<Res${modelNameUpperCamel}VO> getDetail(@RequestBody Req${modelNameUpperCamel}VO req${modelNameUpperCamel}VO);


	/**
	 * 新增${comment}
	 * @param req${modelNameUpperCamel}VO
	 * @return ResponseResult
	 */
	@PostMapping("${apiurl}/add")
	ResponseResult add(@RequestBody Req${modelNameUpperCamel}VO req${modelNameUpperCamel}VO);

	/**
	 * 更新${comment}
	 * @param req${modelNameUpperCamel}VO
	 * @return ResponseResult
	 */
	@PostMapping("${apiurl}/update")
	ResponseResult update(@RequestBody Req${modelNameUpperCamel}VO req${modelNameUpperCamel}VO);

	/**
	 * 删除${comment}
	 * @param req${modelNameUpperCamel}VO
	 * @return ResponseResult
	 */
	@PostMapping("${apiurl}/delete")
	ResponseResult delete(@RequestBody Req${modelNameUpperCamel}VO req${modelNameUpperCamel}VO);



}
