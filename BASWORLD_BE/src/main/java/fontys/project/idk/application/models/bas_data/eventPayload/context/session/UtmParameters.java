package fontys.project.idk.application.models.bas_data.eventPayload.context.session;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@Builder
@EqualsAndHashCode
public class UtmParameters {
    @JsonProperty(value = "source", required = true)
    String source;      

    @JsonProperty(value = "medium", required = true)
    String medium;      

    @JsonProperty(value = "campaign", required = true)
    String campaign;    

    @JsonProperty(value = "term", required = true)
    String term;        

    @JsonProperty(value = "content", required = true)
    String content;     
}
