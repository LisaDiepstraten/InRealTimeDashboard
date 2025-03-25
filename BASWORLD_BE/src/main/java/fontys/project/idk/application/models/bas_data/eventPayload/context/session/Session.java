package fontys.project.idk.application.models.bas_data.eventPayload.context.session;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@Builder
@EqualsAndHashCode
public class Session {
    @JsonProperty(value = "buttSessionId", required = true)
    String buttSessionId;           

    @JsonProperty(value = "vendorClickIds", required = true)
    VendorClicksId vendorClicksIds;  

    @JsonProperty(value = "referer", required = true)
    String referer;                 

    @JsonProperty(value = "utmParameters", required = true)
    UtmParameters utmParameters;    
}
