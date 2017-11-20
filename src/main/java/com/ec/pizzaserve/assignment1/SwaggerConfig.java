package com.ec.pizzaserve.assignment1;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.google.common.base.Predicate;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import static springfox.documentation.builders.PathSelectors.regex;
import static com.google.common.base.Predicates.or;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).groupName("api-doc")
                .apiInfo(apiInfo()).select().paths(paths()).build().useDefaultResponseMessages(false);
    }

    private Predicate<String> paths() {
        return or(regex("/pizza.*"), regex("/order.*"));
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("PizzaServiceAPI v1.0")
                .description("PizzaServiceAPI v1.0 for Enterprises Computing Assignment 1")
                .contact("singh.1@campus.tu-berlin.de")
                .licenseUrl("singh.1@campus.tu-berlin.de").version("1.0").build();
    }

}
