package fontys.project.idk.application.models.bas_data.eventPayload.context.client;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@Builder
@EqualsAndHashCode
public class Device {
    @JsonProperty(value = "type", required = true)
    String type;            

    @JsonProperty(value = "vendor", required = true)
    String vendor;          

    @JsonProperty(value = "model", required = true)
    String model;           

    @JsonProperty(value = "screenWidth", required = true)
    int screenWidth;     

    @JsonProperty(value = "screenHeight", required = true)
    int screenHeight;    
}
