package com.keyworksteste.keyworks.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig{

        @Bean
        public Docket startSwagger() {
            return new Docket(DocumentationType.SWAGGER_2)
                    .select()
                    .apis(RequestHandlerSelectors.basePackage("com.keyworksteste.keyworks.controller"))
                    .paths(PathSelectors.any())
                    .build()
                    .apiInfo(metaData());
        }

    private ApiInfo metaData() {
        return new ApiInfoBuilder()
                .title("KeyWorks Teste API")
                .description("Teste tecnico da KeyWorks")
                .version("1.0.0")
                .build();
    }
}
