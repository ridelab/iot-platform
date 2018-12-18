package org.sselab.iot.platform;

import lombok.RequiredArgsConstructor;
import org.eclipse.leshan.server.LwM2mServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class IotPlatformApplication {

  public static void main(String[] args) {
    SpringApplication.run(IotPlatformApplication.class, args);
  }

  @Bean
  public CommandLineRunner run() {
    return args -> lwM2mServer.start();
  }

  private final LwM2mServer lwM2mServer;

}
