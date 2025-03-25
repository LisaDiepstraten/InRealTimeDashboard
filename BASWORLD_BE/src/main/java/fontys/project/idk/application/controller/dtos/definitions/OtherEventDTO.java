package fontys.project.idk.application.controller.dtos.definitions;

import fontys.project.idk.application.controller.dtos.eventPayload.events.EventDTO;
import fontys.project.idk.application.models.bas_data.eventPayload.events.Event;
import lombok.*;

import java.util.Map;


@Getter
@Builder
public class OtherEventDTO extends EventDTO {
    public OtherEventDTO(){
        eventName = "other";
    }
    public OtherEventDTO(Map<String, Object> additionalData) {
        eventName = "other";
        this.additionalData = additionalData;
    }
}
