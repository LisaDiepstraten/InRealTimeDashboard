package fontys.project.idk.application.models.bas_data.eventPayload.context.client;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@Builder
@EqualsAndHashCode
public class Client {
    @JsonProperty(value = "buttClientId", required = true)
    String buttClientId;                

    @JsonProperty(value = "rawUserAgent", required = true)
    String rawUserAgent;                

    @JsonProperty(value = "vendorClientIds", required = true)
    VendorClientIds vendorClientIds;    

    @JsonProperty(value = "device", required = true)
    Device device;                      

    @JsonProperty(value = "operatingSystem", required = true)
    OperatingSystem operatingSystem;    

    @JsonProperty(value = "browser", required = true)
    Browser browser;                    

    @JsonProperty(value = "engine", required = true)
    Engine engine;                      

    @JsonProperty(value = "geolocation", required = true)
    Geolocation geolocation;            
}
