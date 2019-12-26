package ${serverPackage}.entity;
 
import com.zjbdos.springcloud.common.server.entity.BaseEntity;
import lombok.Data;
import javax.persistence.*;
 
/**
* 实体类
* @package:${clientPackage}.entity
* @author: ${author}
* @create: ${date}
**/
@Data
@Table(name = "${tableName}")
public class ${modelNameUpperCamel} extends BaseEntity {
    <#list entityDataList as data>
    
	 /**
     * ${data.columnComment}
     */
	@Column(name = "${data.columnName_}")
    private ${data.columnType} ${data.columnName};
    </#list>
}