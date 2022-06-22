package com.example.demo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

/**
 * <p>Description: swagger配置类</p>
 * <p>Date: 2021/7/28 10:43 </p>
 *
 * @version v1.0.0
 * @author: cuiyy
 */
@Configuration
@EnableSwagger2WebMvc
public class SwaggerConfig {
    @Value("${swagger.enable:false}")
    private boolean swaggerEnable;
    private static String BASE_PACKAGE = "com.example";
    private static String VERSION = "v1.0.0";

    @Bean
    public Docket createRestApi() {

        return new Docket(DocumentationType.SWAGGER_2)
//                .globalOperationParameters(pars)
                .enable(swaggerEnable)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage(BASE_PACKAGE))//这种方式我们可以通过指定包名的方式，让Swagger 只去某些包下面扫描
//                .apis(RequestHandlerSelectors.any())//这种方式我们可以通过指定包名的方式，让Swagger 只去某些包下面扫描
                .paths(PathSelectors.any())//这种方式可以通过筛选 API 的 url 来进行过滤。
                .build();
    }

    private ApiInfo apiInfo() {
        Contact contact = new Contact("springWeb", "http://test", "123.com");
        return new ApiInfoBuilder()
                .title("Spring Boot中使用Swagger2")
                .description("test服务接口")
                .contact(contact)
                .version(VERSION)
                .build();
    }

}
