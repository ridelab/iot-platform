package org.sselab.iot.platform.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
// Swagger 2.x for Spring Data Rest is not compatible with Spring Boot 2.x
// Waiting for Swagger 3.x
// See <https://github.com/springfox/springfox/issues/2298>
// @Import(SpringDataRestConfiguration.class)
public class SwaggerConfiguration {

  @Bean
  public Docket api() {
    return new Docket(DocumentationType.SWAGGER_2).
      select().
      apis(RequestHandlerSelectors.any()).
      paths(PathSelectors.any()).
      build();
  }

}
