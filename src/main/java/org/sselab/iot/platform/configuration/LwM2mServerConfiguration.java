package org.sselab.iot.platform.configuration;

import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.eclipse.leshan.server.LwM2mServer;
import org.eclipse.leshan.server.californium.LeshanServerBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.sselab.iot.platform.configuration.listener.DatabaseRegistrationListener;

@Slf4j
@Configuration
public class LwM2mServerConfiguration {

  @Bean
  public LwM2mServer initLwM2mServer() {
    val builder = new LeshanServerBuilder();
    val server = builder.build();
    server.getRegistrationService().addListener(new DatabaseRegistrationListener());
    logger.info("Initialized LwM2mServer {}", server);
    return server;
  }

}
