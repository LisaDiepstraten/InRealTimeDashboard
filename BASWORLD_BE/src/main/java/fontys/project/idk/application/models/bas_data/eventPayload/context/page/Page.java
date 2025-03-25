package fontys.project.idk.application.models.bas_data.eventPayload.context.page;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.net.URI;

@Getter
@Builder
@EqualsAndHashCode
public class Page {
    @JsonProperty(value = "title", required = true)
    String title;               

    @JsonProperty(value = "url", required = true)
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    URI url;

    @JsonProperty(value = "canonicalUrl", required = true)
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    URI canonicalUrl;

    @JsonProperty(value = "designSize", required = true)
    DesignSizeEnum designSize;  
}
