package fontys.project.idk.application.controller.dtos.definitions;


import com.fasterxml.jackson.annotation.JsonProperty;
import fontys.project.idk.application.controller.dtos.eventPayload.events.EventDTO;
import fontys.project.idk.application.models.bas_data.eventPayload.events.Event;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;


@Getter
@Builder
public class ElementInteractionEventDTO extends EventDTO {
    public ElementInteractionEventDTO() {
        eventName = "element_interaction";
    }

    public ElementInteractionEventDTO(Data data) {
        eventName = "element_interaction";
        this.data = data;
    }
    public ElementInteractionEventDTO(String elementName, Map<String, Object> additionalData) {
        eventName = "element_interaction";
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
