package com.jason.config;

import com.jason.constant.CommonConstant;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Jason
 * @Date 2022/06/21
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api() {
        List<Parameter> parameters = new ArrayList<>();

        ParameterBuilder tokenBuilder = new ParameterBuilder();
        tokenBuilder.name(CommonConstant.HEADER_KEY).description(CommonConstant.HEADER_KEY_DESC)
                .modelRef(new ModelRef("string")).parameterType("header")
                .required(false);
        parameters.add(tokenBuilder.build());

        return new Docket(DocumentationType.SWAGGER_2)
                .pathMapping("/")
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.jason.controller"))
                .paths(PathSelectors.any())
                .build()
                .globalOperationParameters(parameters);
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Work Tools")
                .description("Work Tools API List")
                .version("1.0")
                .build();
    }
}
