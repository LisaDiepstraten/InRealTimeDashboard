package fontys.project.idk.application.controller.dtos.eventPayload.context.client;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GeolocationDTO {
    @JsonProperty(value = "latitude", required = true)
    String latitude;    

    @JsonProperty(value = "longitude", required = true)
    String longitude;   

    @JsonProperty(value = "country", required = true)
    String country;     

    @JsonProperty(value = "region", required = true)
    String region;      

    @JsonProperty(value = "city", required = true)
    String city;        
}
