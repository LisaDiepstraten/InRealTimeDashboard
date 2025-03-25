package fontys.project.idk.application.controller.dtos.eventPayload.context.session;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class VendorClicksIdDTO {
    @JsonProperty(value = "google", required = true)
    String google; 

    @JsonProperty(value = "bing", required = true)
    String bing; 

    @JsonProperty(value = "facebook", required = true)
    String facebook; 

    @JsonProperty(value = "linkedIn", required = true)
    String linkedIn;
}
