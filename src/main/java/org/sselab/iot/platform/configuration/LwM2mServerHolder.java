package org.sselab.iot.platform.configuration;

import org.eclipse.leshan.server.LwM2mServer;

public class LwM2mServerHolder {

  public static LwM2mServer getServer() {
    if (LwM2mServerHolder.server == null) throw new IllegalStateException("Uninitialized LwM2m Server");
    return LwM2mServerHolder.server;
  }

  static synchronized void setServer(LwM2mServer server) {
    if (LwM2mServerHolder.server != null) throw new IllegalStateException("Duplicate Initialization");
    LwM2mServerHolder.server = server;
  }

  private static LwM2mServer server;

}
