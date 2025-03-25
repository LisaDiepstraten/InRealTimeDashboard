package fontys.project.idk.application.models.bas_data;

import com.fasterxml.jackson.annotation.JsonProperty;
import fontys.project.idk.application.models.bas_data.eventHeaders.EventHeaders;
import fontys.project.idk.application.models.bas_data.eventPayload.EventPayload;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateBWEventRequest {
    @NotBlank
    @JsonProperty(value = "event", required = true)
    String event;

    @NotNull
    @JsonProperty(value = "eventPayload", required = true)
    EventPayload eventPayload;

    @NotNull
    @JsonProperty(value = "eventHeaders", required = true)
    EventHeaders eventHeaders;
}
