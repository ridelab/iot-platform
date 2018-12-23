package org.sselab.iot.platform.configuration.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.eclipse.leshan.core.node.LwM2mResource;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;

// <https://www.baeldung.com/spring-boot-jsoncomponent>
@JsonComponent
public class LwM2mNodeJsonComponent {

  public static class LwM2mResourceSerializer extends JsonSerializer<LwM2mResource> {
    @Override
    public void serialize(LwM2mResource value, JsonGenerator generator, SerializerProvider provider) throws IOException {
      generator.writeStartObject();
      generator.writeNumberField("id", value.getId());
      generator.writeStringField("type", value.getType().name());
      if (value.isMultiInstances()) {
        generator.writeObjectField("values", value.getValues());
      } else {
        generator.writeObjectField("value", value.getValue());
      }
      generator.writeEndObject();
    }
  }

}
