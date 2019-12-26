package ${serverPackage};
import com.zjbdos.springcloud.auth.annotation.EnableZjbdosResourceServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import tk.mybatis.spring.annotation.MapperScan;

import javax.servlet.MultipartConfigElement;

/**
* @package:${serverPackage}.service
* @description:
* @author: ${author}
* @create: ${date}
**/
@EnableDiscoveryClient
@EnableZjbdosResourceServer //允许不经过网关
@EnableFeignClients("com.zjbdos")
@MapperScan({"${serverPackage}.dao"})
@SpringBootApplication(scanBasePackages = "com.zjbdos" )
public class ${className} extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(${className}.class);
    }
    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        //允许上传的文件最大值
        factory.setMaxFileSize("5MB"); //KB,MB
        /// 设置总上传数据总大小
        factory.setMaxRequestSize("2000MB");
        return factory.createMultipartConfig();
    }
    public static void main(String[] args) {
        SpringApplication.run(${className}.class, args);
    }
}
