package fontys.project.idk.application.controller.dtos.eventPayload.application;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ApplicationDTO {
    @JsonProperty(value = "name", required = true)
    String name;

    @JsonProperty(value = "version", required = true)
    String version;

    @JsonProperty(value = "environment", required = true)
    String environment;
}
