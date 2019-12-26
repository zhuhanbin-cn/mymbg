package ${serverPackage}.config;

import com.fasterxml.classmate.TypeResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


/**
 * @package: ${serverPackage}.config
 * @description:  swagger配置
 * @author: ${author}
 * @create: ${date}
 **/
 
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Autowired
    private TypeResolver typeResolver;

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("接口")
                .description("这是中心接口")
                .version("1.0.0")
                .build();
    }

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.zjbdos.ebb"))
                .paths(PathSelectors.any())
                .build();
              //  .alternateTypeRules(new AlternateTypeRule(typeResolver.resolve(ResponseResult.class), typeResolver.resolve(WildcardType.class)));
    }
}