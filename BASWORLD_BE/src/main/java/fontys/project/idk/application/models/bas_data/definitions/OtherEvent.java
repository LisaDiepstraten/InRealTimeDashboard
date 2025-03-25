package fontys.project.idk.application.models.bas_data.definitions;

import fontys.project.idk.application.models.bas_data.eventPayload.events.Event;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Getter
@Builder

public class OtherEvent extends Event {

    public OtherEvent() {
        eventName = "other";
    }

    public OtherEvent(Map<String, Object> additionalData) {
        eventName = "other";
        this.additionalData = additionalData;
    }
}
