package ${clientPackage}.feign.factory;

import ${clientPackage}.feign.${modelNameUpperCamel}ServiceFeign;
import ${clientPackage}.feign.fallback.${modelNameUpperCamel}ServiceFeignImpl;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @package: ${clientPackage}.feign.factory
 * @description: 字典feignservice异常back工厂
 * @author: ${author}
 * @create: ${date}
 **/
@Component
public class ${modelNameUpperCamel}ServiceFeignFactory implements FallbackFactory<${modelNameUpperCamel}ServiceFeign> {
    @Override
    public ${modelNameUpperCamel}ServiceFeign create(Throwable throwable) {
        ${modelNameUpperCamel}ServiceFeignImpl remoteMenuServiceFallback = new ${modelNameUpperCamel}ServiceFeignImpl();
        remoteMenuServiceFallback.setCause(throwable);
        return remoteMenuServiceFallback;
    }
}