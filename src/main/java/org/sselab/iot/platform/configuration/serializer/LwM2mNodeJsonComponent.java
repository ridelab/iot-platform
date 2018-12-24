package org.sselab.iot.platform.configuration.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;
import lombok.val;
import org.eclipse.leshan.core.model.ResourceModel;
import org.eclipse.leshan.core.node.*;
import org.springframework.boot.jackson.JsonComponent;
import org.sselab.iot.platform.util.ExceptionalFunction;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.stream.StreamSupport;

// <https://www.baeldung.com/spring-boot-jsoncomponent>
@JsonComponent
public class LwM2mNodeJsonComponent {

  public static class LwM2mResourceSerializer extends JsonSerializer<LwM2mResource> {
    @Override
    public void serialize(LwM2mResource value, JsonGenerator generator, SerializerProvider provider) throws IOException {
      generator.writeStartObject();
      generator.writeNumberField("id", value.getId());
      generator.writeObjectField("type", value.getType());
      if (value.isMultiInstances()) {
        generator.writeObjectField("values", value.getValues());
      } else {
        generator.writeObjectField("value", value.getValue());
      }
      generator.writeEndObject();
    }
  }

  public static class LwM2mNodeDeserializer extends JsonDeserializer<LwM2mNode> {
    @Override
    public LwM2mNode deserialize(JsonParser parser, DeserializationContext context) throws IOException {
      JsonNode node = parser.getCodec().readTree(parser);
      if (!node.isObject()) throw new IllegalArgumentException();
      if (!node.has("id")) throw new IllegalArgumentException();
      val id = node.get("id").asInt();
      if (node.has("instances")) {
        Iterable<JsonNode> elements = () -> node.get("instances").elements();
        @SuppressWarnings("SuspiciousToArrayCall")
        val instances = StreamSupport.stream(elements.spliterator(), false).
          map(ExceptionalFunction.unchecked(n -> parser.getCodec().treeToValue(n, LwM2mNode.class))).
          toArray(LwM2mObjectInstance[]::new);
        return new LwM2mObject(id, instances);
      }
      if (node.has("resources")) {
        Iterable<JsonNode> elements = () -> node.get("resources").elements();
        @SuppressWarnings("SuspiciousToArrayCall")
        val resources = StreamSupport.stream(elements.spliterator(), false).
          map(ExceptionalFunction.unchecked(n -> parser.getCodec().treeToValue(n, LwM2mNode.class))).
          toArray(LwM2mResource[]::new);
        return new LwM2mObjectInstance(id, resources);
      }
      if (node.has("values")) {
        val type = ResourceModel.Type.valueOf(node.get("type").asText().toUpperCase());
        val values = new LinkedHashMap<Integer, Object>();
        val it = node.get("values").fields();
        while (it.hasNext()) {
          val t = it.next();
          val key = Integer.parseInt(t.getKey());
          val value = deserializeValue(t.getValue(), type, parser);
          values.put(key, value);
        }
        return LwM2mMultipleResource.newResource(id, values, type);
      }
      if (node.has("value")) {
        val type = ResourceModel.Type.valueOf(node.get("type").asText().toUpperCase());
        val value = deserializeValue(node.get("value"), type, parser);
        return LwM2mSingleResource.newResource(id, value, type);
      }
      throw new IllegalArgumentException();
    }
  }

  private static Object deserializeValue(JsonNode value, ResourceModel.Type type, JsonParser parser) throws JsonProcessingException {
    switch (type) {
      case STRING:
        return value.asText();
      case INTEGER:
        return value.asLong();
      case FLOAT:
        return value.asDouble();
      case BOOLEAN:
        return value.asBoolean();
      case OPAQUE:
        return parser.getCodec().treeToValue(value, byte[].class);
      case TIME:
        return parser.getCodec().treeToValue(value, java.util.Date.class);
      case OBJLNK:
        return ObjectLink.fromPath(value.asText());
    }
    throw new IllegalArgumentException();
  }

}
