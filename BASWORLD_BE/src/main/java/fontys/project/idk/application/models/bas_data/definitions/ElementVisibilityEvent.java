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

public class ElementVisibilityEvent extends Event {

    @JsonProperty(value = "data", required = true)
    Data data;

    public ElementVisibilityEvent() {
        eventName = "element_visibility";
    }

    public ElementVisibilityEvent(Data data) {
        eventName = "element_visibility";
        this.data = data;
    }

    public ElementVisibilityEvent(String elementName, Map<String, Object> additionalData) {
        eventName = "element_visibility";
        this.data = new ElementVisibilityEvent.Data(elementName);
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
