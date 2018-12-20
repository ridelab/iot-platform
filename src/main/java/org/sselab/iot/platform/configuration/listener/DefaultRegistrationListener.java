package org.sselab.iot.platform.configuration.listener;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.leshan.core.observation.Observation;
import org.eclipse.leshan.server.registration.Registration;
import org.eclipse.leshan.server.registration.RegistrationListener;
import org.eclipse.leshan.server.registration.RegistrationUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Slf4j
@Primary
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class DefaultRegistrationListener implements RegistrationListener {

  @Override
  public void registered(
    Registration registration,
    Registration previousRegistration,
    Collection<Observation> previousObservations
  ) {
    logger.trace("DefaultRegistrationListener#registered");
    logger.trace("registration = {}", registration);
    logger.trace("previousRegistration = {}", previousRegistration);
    logger.trace("previousObservations = {}", previousObservations);
    // TODO
  }

  @Override
  public void updated(
    RegistrationUpdate update,
    Registration updatedRegistration,
    Registration previousRegistration
  ) {
    logger.trace("DefaultRegistrationListener#updated");
    logger.trace("update = {}", update);
    logger.trace("updatedRegistration = {}", updatedRegistration);
    logger.trace("previousRegistration = {}", previousRegistration);
    // TODO
  }

  @Override
  public void unregistered(
    Registration registration,
    Collection<Observation> observations,
    boolean expired, Registration newRegistration
  ) {
    logger.trace("DefaultRegistrationListener#unregistered");
    logger.trace("registration = {}", registration);
    logger.trace("observations = {}", observations);
    logger.trace("newRegistration = {}", newRegistration);
    // TODO
  }

}
