package fontys.project.idk.application.controller.dtos.eventPayload.context.client;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ClientDTO {
    @JsonProperty(value = "buttClientId", required = true)
    String buttClientId;                

    @JsonProperty(value = "rawUserAgent", required = true)
    String rawUserAgent;                

    @JsonProperty(value = "vendorClientIds", required = true)
    VendorClientIdsDTO vendorClientIds;

    @JsonProperty(value = "device", required = true)
    DeviceDTO device;

    @JsonProperty(value = "operatingSystem", required = true)
    OperatingSystemDTO operatingSystem;

    @JsonProperty(value = "browser", required = true)
    BrowserDTO browser;

    @JsonProperty(value = "engine", required = true)
    EngineDTO engine;

    @JsonProperty(value = "geolocation", required = true)
    GeolocationDTO geolocation;
}
