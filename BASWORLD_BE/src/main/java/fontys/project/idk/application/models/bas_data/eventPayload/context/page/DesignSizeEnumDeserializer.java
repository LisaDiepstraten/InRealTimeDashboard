package fontys.project.idk.application.models.bas_data.eventPayload.context.page;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import lombok.EqualsAndHashCode;

import java.io.IOException;

@EqualsAndHashCode
public class DesignSizeEnumDeserializer extends JsonDeserializer<DesignSizeEnum> {
    @Override
    public DesignSizeEnum deserialize(JsonParser p, DeserializationContext ctxt)
            throws IOException, JsonProcessingException {
        String value = p.getText();
        return DesignSizeEnum.fromString(value);
    }
}