package fontys.project.idk.application.models.bas_data.eventPayload.context.client;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@Builder
@EqualsAndHashCode
public class Browser {
    @JsonProperty(value = "name", required = true)
    String name;

    @JsonProperty(value = "version", required = true)
    String version;             

    @JsonProperty(value = "viewportWidth", required = true)
    int viewportWidth;          

    @JsonProperty(value = "viewportHeight", required = true)
    int viewportHeight;

    @JsonProperty(value = "language", required = true)
    String language;            

    @JsonProperty(value = "cookiesEnabled", required = true)
    boolean cookiesEnabled;     

    @JsonProperty(value = "javaScriptEnabled", required = true)
    boolean javaScriptEnabled;  

    @JsonProperty(value = "doNotTrackEnabled", required = true)
    boolean doNotTrackEnabled;  
}
