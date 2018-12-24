package org.sselab.iot.platform.configuration;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.eclipse.leshan.server.LwM2mServer;
import org.eclipse.leshan.server.californium.LeshanServerBuilder;
import org.eclipse.leshan.server.observation.ObservationListener;
import org.eclipse.leshan.server.registration.RegistrationListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class LwM2mServerConfiguration {

  private final RegistrationListener registrationListener;

  private final ObservationListener observationListener;

  @Bean
  public LwM2mServer initLwM2mServer() {
    val builder = new LeshanServerBuilder();
    val server = builder.build();
    server.getRegistrationService().addListener(registrationListener);
    server.getObservationService().addListener(observationListener);
    logger.info("Initialized LwM2mServer {}", server);
    LwM2mServerHolder.setServer(server);
    return server;
  }

}
