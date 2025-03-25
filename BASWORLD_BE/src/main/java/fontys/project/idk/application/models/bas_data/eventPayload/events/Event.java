package fontys.project.idk.application.models.bas_data.eventPayload.events;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import fontys.project.idk.application.models.bas_data.definitions.*;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "eventName")
@JsonSubTypes({
        @JsonSubTypes.Type(value = PageVisitEvent.class, name = "page_visit"),
        @JsonSubTypes.Type(value = ElementVisibilityEvent.class, name = "element_visibility"),
        @JsonSubTypes.Type(value = ElementInteractionEvent.class, name = "element_interaction"),
        @JsonSubTypes.Type(value = ViewItemListEvent.class, name = "view_item_list"),
        @JsonSubTypes.Type(value = SelectItemEvent.class, name = "select_item"),
        @JsonSubTypes.Type(value = OtherEvent.class, name = "other")
})
public abstract class Event {
    @JsonProperty(value = "eventName", required = true)
    public String eventName;

    @JsonProperty(value = "additionalData", required = false)
    public Map<String, Object> additionalData = new HashMap<>();;
}
