package fontys.project.idk.application.models.bas_data;

import com.fasterxml.jackson.annotation.JsonProperty;
import fontys.project.idk.application.models.bas_data.eventHeaders.EventHeaders;
import fontys.project.idk.application.models.bas_data.eventPayload.EventPayload;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.util.Objects;

@Data
@Builder
@Getter
public class BasWorldEvent {
    @JsonProperty(value = "event", required = true)
    String event;

    @JsonProperty(value = "eventPayload", required = true)
    EventPayload eventPayload;

    @JsonProperty(value = "eventHeaders", required = true)
    EventHeaders eventHeaders;


}
