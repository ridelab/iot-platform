package org.sselab.iot.platform.configuration.listener;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.leshan.core.observation.Observation;
import org.eclipse.leshan.core.response.ObserveResponse;
import org.eclipse.leshan.server.observation.ObservationListener;
import org.eclipse.leshan.server.registration.Registration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Slf4j
@Primary
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DefaultObservationListener implements ObservationListener {

  @Override
  public void newObservation(Observation observation, Registration registration) {
    logger.trace("DefaultObservationListener#newObservation");
    logger.trace("observation = {}", observation);
    logger.trace("registration = {}", registration);
    // TODO
  }

  @Override
  public void cancelled(Observation observation) {
    logger.trace("DefaultObservationListener#cancelled");
    logger.trace("observation = {}", observation);
    // TODO
  }

  @Override
  public void onResponse(Observation observation, Registration registration, ObserveResponse response) {
    logger.trace("DefaultObservationListener#onResponse");
    logger.trace("observation = {}", observation);
    logger.trace("registration = {}", registration);
    logger.trace("response = {}", response);
    // TODO
  }

  @Override
  public void onError(Observation observation, Registration registration, Exception error) {
    logger.trace("DefaultObservationListener#onError");
    logger.trace("observation = {}", observation);
    logger.trace("registration = {}", registration);
    logger.trace("error = {}", error);
    // TODO
  }

}
