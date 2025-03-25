package fontys.project.idk.application.models.bas_data.eventPayload.context.client;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@Builder
@EqualsAndHashCode
public class VendorClientIds {
    @JsonProperty(value = "googleAnalytics", required = true)
    String googleAnalytics;     

    @JsonProperty(value = "bing", required = true)
    String bing;                

    @JsonProperty(value = "facebook", required = true)
    String facebook;            
}
