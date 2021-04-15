package com.itheima.travel;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

import java.util.function.Predicate;

@Configuration
//开启swagger2的webmvc支持
@EnableSwagger2WebMvc
public class SwaggerConfig {

    @Bean
    public Docket createRestApi() {
        // 构建API文档  文档类型为swagger2
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                // 配置 api扫描路径
                .apis(RequestHandlerSelectors.basePackage("com.itheima.travel.controller"))
                // 指定路径的设置  any代表所有路径
                .paths(PathSelectors.any())
                // api的基本信息
                .build().apiInfo(new ApiInfoBuilder()
                        // api文档名称
                        .title("SSM黑马旅游Swagger接口文档")
                        // api文档描述
                        .description("SSM黑马旅游，描述信息......")
                        .contact(new Contact("黄星成", "http://www.itcatst.com", "58948528@qq.com"))
                        // api文档版本
                        .version("1.0") // 版本
                        .build());
    }

}
