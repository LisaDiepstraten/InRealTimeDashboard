package fontys.project.idk.application.controller.dtos.eventPayload.context.client;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class VendorClientIdsDTO {
    @JsonProperty(value = "googleAnalytics", required = true)
    String googleAnalytics;     

    @JsonProperty(value = "bing", required = true)
    String bing;                

    @JsonProperty(value = "facebook", required = true)
    String facebook;            
}
