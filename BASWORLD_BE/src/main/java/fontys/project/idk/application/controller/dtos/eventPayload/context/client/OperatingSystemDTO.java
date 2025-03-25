package fontys.project.idk.application.controller.dtos.eventPayload.context.client;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class OperatingSystemDTO {
    @JsonProperty(value = "name", required = true)
    String name;

    @JsonProperty(value = "version", required = true)
    String version;
}
