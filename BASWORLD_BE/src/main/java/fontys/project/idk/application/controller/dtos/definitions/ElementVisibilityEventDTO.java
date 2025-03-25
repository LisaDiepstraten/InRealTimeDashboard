package fontys.project.idk.application.controller.dtos.definitions;


import com.fasterxml.jackson.annotation.JsonProperty;
import fontys.project.idk.application.controller.dtos.eventPayload.events.EventDTO;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Getter
@Builder
public class ElementVisibilityEventDTO extends EventDTO {
    public ElementVisibilityEventDTO() {
        eventName = "element_visibility";
    }
    public ElementVisibilityEventDTO(Data data) {
        eventName = "element_visibility";
        this.data = data;
    }
    public ElementVisibilityEventDTO(String elementName, Map<String, Object> additionalData) {
        eventName = "element_visibility";
        this.data = new Data(elementName);
        this.additionalData = additionalData;
    }

    @Getter
    @Builder
    public static class Data{
        @JsonProperty(value = "elementName", required = true)
        String elementName;
    }

    @JsonProperty(value = "data", required = true)
    Data data;
}
