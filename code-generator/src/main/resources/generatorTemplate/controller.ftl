package ${serverPackage}.controller;

import ${clientPackage}.vo.Res${modelNameUpperCamel}VO;
import ${clientPackage}.vo.Req${modelNameUpperCamel}VO;
import ${serverPackage}.service.${modelNameUpperCamel}Service;
import com.zjbdos.springcloud.common.core.vo.ResponseResult; 
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController; 
import java.util.List;

 
/**
* @package:${serverPackage}.controller
* @author: ${author}
* @create: ${date}
**/

@RestController
@RequestMapping("/api/${modelNameLowerCamel}")
@Api(tags = "${modelNameLowerCamel}")

public class ${modelNameUpperCamel}Controller {
 
    @Autowired
    private ${modelNameUpperCamel}Service ${modelNameLowerCamel}Service;
	
	@ApiOperation(value = "获取${comment}列表", notes = "获取${comment}列表")
    @ApiResponse(code = 200, response = ResponseResult.class, message = "success")
    @PostMapping("/getList")
	public ResponseResult<List<Res${modelNameUpperCamel}VO>> getList(Req${modelNameUpperCamel}VO req${modelNameUpperCamel}VO) {
        return ${modelNameLowerCamel}Service.getList(${modelNameUpperCamel}Converter.INSTANCE.req${modelNameUpperCamel}VO2${modelNameUpperCamel}DTO(req${modelNameUpperCamel}VO));
    }
	
	@ApiOperation(value = "分页获取${comment}列表", notes = "获取${comment}列表")
    @ApiResponse(code = 200, response = ResponseResult.class, message = "success")
    @PostMapping("/get${modelNameUpperCamel}ListPageSize")
    public ResponseResult<Res${modelNameUpperCamel}VO> get${modelNameUpperCamel}ListPageSize(@RequestBody Req${modelNameUpperCamel}VO req${modelNameUpperCamel}VO) {
        return ${modelNameLowerCamel}Service.getListPageSize(${modelNameUpperCamel}Converter.INSTANCE.req${modelNameUpperCamel}VO2${modelNameUpperCamel}DTO(req${modelNameUpperCamel}VO));
    }
	
	@ApiOperation(value = "获取${comment}详细信息", notes = "获取${comment}详细信息")
    @ApiResponse(code = 200, response = ResponseResult.class, message = "success")
    @PostMapping("/getDetail")
    public ResponseResult<Res${modelNameUpperCamel}VO> getDetail(@RequestBody Req${modelNameUpperCamel}VO req${modelNameUpperCamel}VO) {
        return ${modelNameLowerCamel}Service.getDetail(${modelNameUpperCamel}Converter.INSTANCE.req${modelNameUpperCamel}VO2${modelNameUpperCamel}DTO(req${modelNameUpperCamel}VO));
    }
 
    @ApiOperation(value = "新增${comment}", notes = "新增${comment}")
    @ApiResponse(code = 200, response = ResponseResult.class, message = "success")
    @PostMapping("/add")
    public ResponseResult add(@RequestBody Req${modelNameUpperCamel}VO req${modelNameUpperCamel}VO) {
        return ${modelNameLowerCamel}Service.add(${modelNameUpperCamel}Converter.INSTANCE.req${modelNameUpperCamel}VO2${modelNameUpperCamel}DTO(req${modelNameUpperCamel}VO));
    }

    @ApiOperation(value = "删除${comment}", notes = "删除${comment}")
    @ApiResponse(code = 200, response = ResponseResult.class, message = "success")
    @PostMapping("/delete")
    public ResponseResult delete(@RequestBody ReqIdVO reqIdVOs) {
        return ${modelNameLowerCamel}Service.delete(reqIdVOs.getIds());
    }

    @ApiOperation(value = "更新${comment}", notes = "更新${comment}")
    @ApiResponse(code = 200, response = ResponseResult.class, message = "success")
    @PostMapping("/update")
    public ResponseResult update(@RequestBody Req${modelNameUpperCamel}VO req${modelNameUpperCamel}VO) {
        return ${modelNameLowerCamel}Service.update(${modelNameUpperCamel}Converter.INSTANCE.req${modelNameUpperCamel}VO2${modelNameUpperCamel}DTO(req${modelNameUpperCamel}VO));
    }


 
}