package fontys.project.idk.application.controller.dtos.eventPayload.events;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import fontys.project.idk.application.controller.dtos.definitions.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;


@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "eventName")
@JsonSubTypes({
        @JsonSubTypes.Type(value = PageVisitEventDTO.class, name = "page_visit"),
        @JsonSubTypes.Type(value = ElementVisibilityEventDTO.class, name = "element_visibility"),
        @JsonSubTypes.Type(value = ElementInteractionEventDTO.class, name = "element_interaction"),
        @JsonSubTypes.Type(value = ViewItemListEventDTO.class, name = "view_item_list"),
        @JsonSubTypes.Type(value = SelectItemEventDTO.class, name = "select_item"),
        @JsonSubTypes.Type(value = OtherEventDTO.class, name = "other")
})
@Getter
public abstract class EventDTO {
    @JsonProperty(value = "eventName", required = true)
    public String eventName;

    @JsonProperty(value = "additionalData", required = false)
    public Map<String, Object> additionalData = new HashMap<>();;
}
