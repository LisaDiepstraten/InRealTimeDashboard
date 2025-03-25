package fontys.project.idk.application.controller.dtos.eventPayload.context.page;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;

public class DesignSizeEnumDeserializerDTO extends JsonDeserializer<DesignSizeEnumDTO> {
    @Override
    public DesignSizeEnumDTO deserialize(JsonParser p, DeserializationContext ctxt)
            throws IOException, JsonProcessingException {
        String value = p.getText();
        return DesignSizeEnumDTO.fromString(value);
    }
}