package fontys.project.idk.application.models.bas_data.eventPayload.context.session;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@Builder
@EqualsAndHashCode
public class VendorClicksId {
    @JsonProperty(value = "google", required = true)
    String google; 

    @JsonProperty(value = "bing", required = true)
    String bing; 

    @JsonProperty(value = "facebook", required = true)
    String facebook; 

    @JsonProperty(value = "linkedIn", required = true)
    String linkedIn;
}
