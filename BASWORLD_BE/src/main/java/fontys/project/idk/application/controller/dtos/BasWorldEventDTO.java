package fontys.project.idk.application.controller.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import fontys.project.idk.application.controller.dtos.eventHeaders.EventHeadersDTO;
import fontys.project.idk.application.controller.dtos.eventPayload.EventPayloadDTO;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Data
@Builder
@Getter
public class BasWorldEventDTO {
    @JsonProperty(value = "event", required = true)
    String event;

    @JsonProperty(value = "eventPayload", required = true)
    EventPayloadDTO eventPayload;

    @JsonProperty(value = "eventHeaders", required = true)
    EventHeadersDTO eventHeaders;
}
