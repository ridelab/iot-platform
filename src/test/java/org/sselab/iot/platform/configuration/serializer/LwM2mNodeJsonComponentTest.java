package org.sselab.iot.platform.configuration.serializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class LwM2mNodeJsonComponentTest {

  @Autowired
  private ObjectMapper objectMapper;

  @Test
  public void serializeLwM2mObject() {
  }

  @Test
  public void serializeLwM2mObjectInstance() {
  }

  @Test
  public void serializeLwM2mMultipleResource() {
  }

  @Test
  public void serializeLwM2mSingleResource() {
  }

  @Test
  public void deserializeLwM2mObject() {
  }

  @Test
  public void deserializeLwM2mObjectInstance() {
  }

  @Test
  public void deserializeLwM2mMultipleResource() {
  }

  @Test
  public void deserializeLwM2mSingleResource() {
  }

}
