package fontys.project.idk.application.controller.dtos.eventPayload;


import com.fasterxml.jackson.annotation.JsonProperty;
import fontys.project.idk.application.controller.dtos.eventPayload.application.ApplicationDTO;
import fontys.project.idk.application.controller.dtos.eventPayload.context.ContextDTO;
import fontys.project.idk.application.controller.dtos.eventPayload.events.EventDTO;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class EventPayloadDTO {
    @JsonProperty(value = "application", required = true)
    ApplicationDTO application;

    @JsonProperty(value = "context", required = true)
    ContextDTO context;

    @JsonProperty(value = "event", required = true)
    EventDTO event;
}
