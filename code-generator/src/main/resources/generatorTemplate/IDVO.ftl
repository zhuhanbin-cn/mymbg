package ${clientPackage}.vo;
 
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
 
/**
* 传参类
* 根据需要删减无效参数
* @package:${clientPackage}.vo
* @author: ${author}
* @create: ${date}
**/
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel
public class ${className} {
    @ApiModelProperty(example = "id",value = "编号")
    private String id;
	@ApiModelProperty(example = "ids", value = "编号")
    private List<String> ids;
}