package org.sselab.iot.platform.configuration.serializer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.val;
import org.eclipse.leshan.core.node.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;

@SpringBootTest
@RunWith(SpringRunner.class)
public class LwM2mNodeJsonComponentTest {

  @Autowired
  private ObjectMapper objectMapper;

  @Test
  public void serializeLwM2mObject() throws JsonProcessingException {
    val lwM2mObject = new LwM2mObject(
      9,
      new LwM2mObjectInstance(
        0,
        LwM2mSingleResource.newFloatResource(0, 0.0)
      ),
      new LwM2mObjectInstance(
        1,
        LwM2mSingleResource.newFloatResource(0, 0.0)
      )
    );
    val json = "{\"id\":9,\"instances\":{\"0\":{\"id\":0,\"resources\":{\"0\":{\"id\":0,\"type\":\"FLOAT\",\"value\":0.0}}},\"1\":{\"id\":1,\"resources\":{\"0\":{\"id\":0,\"type\":\"FLOAT\",\"value\":0.0}}}}}";
    assertEquals(json, objectMapper.writeValueAsString(lwM2mObject));
  }

  @Test
  public void serializeLwM2mObjectInstance() throws JsonProcessingException {
    val lwM2mObjectInstance = new LwM2mObjectInstance(
      0,
      LwM2mSingleResource.newFloatResource(0, 0.0),
      LwM2mSingleResource.newDateResource(1, Date.from(Instant.EPOCH))
    );
    val json = "{\"id\":0,\"resources\":{\"0\":{\"id\":0,\"type\":\"FLOAT\",\"value\":0.0},\"1\":{\"id\":1,\"type\":\"TIME\",\"value\":\"1970-01-01T00:00:00.000+0000\"}}}";
    assertEquals(json, objectMapper.writeValueAsString(lwM2mObjectInstance));
  }

  @Test
  public void serializeLwM2mMultipleResource() throws JsonProcessingException {
    val lwM2mMultipleResource = LwM2mMultipleResource.newFloatResource(
      0,
      new HashMap<Integer, Double>() {{
        put(0, 0.0);
        put(1, 1.0);
      }}
    );
    val json = "{\"id\":0,\"type\":\"FLOAT\",\"values\":{\"0\":0.0,\"1\":1.0}}";
    assertEquals(json, objectMapper.writeValueAsString(lwM2mMultipleResource));
  }

  @Test
  public void serializeLwM2mSingleResource() throws JsonProcessingException {
    val lwM2mSingleResource = LwM2mSingleResource.newFloatResource(
      0,
      0.0
    );
    val json = "{\"id\":0,\"type\":\"FLOAT\",\"value\":0.0}";
    assertEquals(json, objectMapper.writeValueAsString(lwM2mSingleResource));
  }

  @Test
  public void deserializeLwM2mObject() throws IOException {
    val lwM2mObject = new LwM2mObject(
      9,
      new LwM2mObjectInstance(
        0,
        LwM2mSingleResource.newFloatResource(0, 0.0)
      ),
      new LwM2mObjectInstance(
        1,
        LwM2mSingleResource.newFloatResource(0, 0.0)
      )
    );
    val json = "{\"id\":9,\"instances\":{\"0\":{\"id\":0,\"resources\":{\"0\":{\"id\":0,\"type\":\"FLOAT\",\"value\":0.0}}},\"1\":{\"id\":1,\"resources\":{\"0\":{\"id\":0,\"type\":\"FLOAT\",\"value\":0.0}}}}}";
    assertEquals(lwM2mObject, objectMapper.readValue(json, LwM2mNode.class));
  }

  @Test
  public void deserializeLwM2mObjectInstance() throws IOException {
    val lwM2mObjectInstance = new LwM2mObjectInstance(
      0,
      LwM2mSingleResource.newFloatResource(0, 0.0),
      LwM2mSingleResource.newDateResource(1, Date.from(Instant.EPOCH))
    );
    val json = "{\"id\":0,\"resources\":{\"0\":{\"id\":0,\"type\":\"FLOAT\",\"value\":0.0},\"1\":{\"id\":1,\"type\":\"TIME\",\"value\":\"1970-01-01T00:00:00.000+0000\"}}}";
    assertEquals(lwM2mObjectInstance, objectMapper.readValue(json, LwM2mNode.class));
  }

  @Test
  public void deserializeLwM2mMultipleResource() throws IOException {
    val lwM2mMultipleResource = LwM2mMultipleResource.newFloatResource(
      0,
      new HashMap<Integer, Double>() {{
        put(0, 0.0);
        put(1, 1.0);
      }}
    );
    val json = "{\"id\":0,\"type\":\"FLOAT\",\"values\":{\"0\":0.0,\"1\":1.0}}";
    assertEquals(lwM2mMultipleResource, objectMapper.readValue(json, LwM2mNode.class));
  }

  @Test
  public void deserializeLwM2mSingleResource() throws IOException {
    val lwM2mSingleResource = LwM2mSingleResource.newFloatResource(
      0,
      0.0
    );
    val json = "{\"id\":0,\"type\":\"FLOAT\",\"value\":0.0}";
    assertEquals(lwM2mSingleResource, objectMapper.readValue(json, LwM2mNode.class));
  }

}
