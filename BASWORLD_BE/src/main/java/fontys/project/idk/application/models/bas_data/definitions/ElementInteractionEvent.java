package fontys.project.idk.application.models.bas_data.definitions;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import fontys.project.idk.application.models.bas_data.eventPayload.events.Event;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Map;

@Getter
@Builder

public class ElementInteractionEvent extends Event {

    @JsonProperty(value = "data", required = true)
    Data data;

    public ElementInteractionEvent() {
        eventName = "element_interaction";
    }

    public ElementInteractionEvent(Data data) {
        eventName = "element_interaction";
        this.data = data;
    }

    public ElementInteractionEvent(String elementName, Map<String, Object> additionalData) {
        eventName = "element_interaction";
        this.data = new ElementInteractionEvent.Data(elementName);
        this.additionalData = additionalData;
    }

    @Getter
    @Builder
    @NoArgsConstructor

    public static class Data {
        @JsonProperty(value = "elementName", required = true)
        String elementName;

        @JsonCreator
        public Data(@JsonProperty("elementName") String elementName) {
            this.elementName = elementName;
        }
    }
}
