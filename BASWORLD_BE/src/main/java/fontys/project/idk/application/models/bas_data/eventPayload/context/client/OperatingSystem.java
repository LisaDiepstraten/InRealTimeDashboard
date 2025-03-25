package fontys.project.idk.application.models.bas_data.eventPayload.context.client;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@Builder
@EqualsAndHashCode
public class OperatingSystem {
    @JsonProperty(value = "name", required = true)
    String name;

    @JsonProperty(value = "version", required = true)
    String version;
}
