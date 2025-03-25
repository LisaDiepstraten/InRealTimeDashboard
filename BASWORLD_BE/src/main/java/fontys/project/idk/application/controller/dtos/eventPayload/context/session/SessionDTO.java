package fontys.project.idk.application.controller.dtos.eventPayload.context.session;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SessionDTO {
    @JsonProperty(value = "buttSessionId", required = true)
    String buttSessionId;           

    @JsonProperty(value = "vendorClickIds", required = true)
    VendorClicksIdDTO vendorClicksIds;

    @JsonProperty(value = "referer", required = true)
    String referer;                 

    @JsonProperty(value = "utmParameters", required = true)
    UtmParametersDTO utmParameters;
}
