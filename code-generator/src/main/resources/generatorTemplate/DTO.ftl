package ${clientPackage}.dto;
 
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
 
/**
* 传参类
* 根据需要删减无效参数
* @package:${clientPackage}.dto
* @author: ${author}
* @create: ${date}
**/
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel
public class ${className} {
    <#list baseDataList as data>
    @ApiModelProperty(example = "${data.columnComment}",value = "${data.columnComment}")
    private ${data.columnType} ${data.columnName};
    </#list>
	
	@ApiModelProperty(example = "当前页", value = "当前页")
    private Integer current;
    @ApiModelProperty(example = "每页记录数", value = "每页记录数")
    private Integer pageSize;
}