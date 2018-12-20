package com.qinhetea.configuration;

import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// <https://orchidflower.gitee.io/2018/06/22/Handling-Bigint-using-Jackson-in-Springboot/>
@Configuration
public class JacksonConfiguration {

  @Bean
  public Jackson2ObjectMapperBuilderCustomizer jackson2ObjectMapperBuilderCustomizer() {
    return builder -> builder.
      serializerByType(Long.class, ToStringSerializer.instance).
      serializerByType(Long.TYPE, ToStringSerializer.instance);
  }

}
