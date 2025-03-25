package fontys.project.idk.application.models.bas_data.eventPayload;


import com.fasterxml.jackson.annotation.JsonProperty;
import fontys.project.idk.application.models.bas_data.eventPayload.application.BASWorldApplication;
import fontys.project.idk.application.models.bas_data.eventPayload.context.Context;
import fontys.project.idk.application.models.bas_data.eventPayload.events.Event;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
@EqualsAndHashCode
@Getter
@Builder
@AllArgsConstructor
public class EventPayload {
    @JsonProperty(value = "application", required = true)
    BASWorldApplication application;

    @JsonProperty(value = "context", required = true)
    Context context;

    @JsonProperty(value = "event", required = true)
    Event event;
}
