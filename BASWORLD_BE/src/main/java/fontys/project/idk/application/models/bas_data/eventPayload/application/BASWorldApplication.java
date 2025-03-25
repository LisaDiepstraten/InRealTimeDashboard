package fontys.project.idk.application.models.bas_data.eventPayload.application;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
@EqualsAndHashCode
public class BASWorldApplication {
    @JsonProperty(value = "name", required = true)
    String name;

    @JsonProperty(value = "version", required = true)
    String version;

    @JsonProperty(value = "environment", required = true)
    String environment;
}
