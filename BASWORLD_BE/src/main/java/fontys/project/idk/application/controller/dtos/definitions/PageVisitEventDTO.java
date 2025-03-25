package fontys.project.idk.application.controller.dtos.definitions;


import com.fasterxml.jackson.annotation.JsonProperty;
import fontys.project.idk.application.controller.dtos.eventPayload.events.EventDTO;
import fontys.project.idk.application.models.bas_data.eventPayload.events.Event;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Getter
@Builder
public class PageVisitEventDTO extends EventDTO {

    public PageVisitEventDTO() {
        eventName = "page_visit";
    }

    public PageVisitEventDTO(Data data) {
        eventName = "page_visit";
        this.data = data;
    }
    public PageVisitEventDTO(String pageType, String pageLanguage, Map<String, Object> additionalData) {
        eventName = "page_visit";
        this.data = new Data(pageType, pageLanguage);
        this.additionalData = additionalData;
    }




    @Builder
    @Getter
    public static class Data{
        @JsonProperty(value = "pageType", required = true)
        String pageType;

        @JsonProperty(value = "pageLanguage", required = true)
        String pageLanguage;
    }

    @JsonProperty(value = "data", required = true)
    Data data;
}
