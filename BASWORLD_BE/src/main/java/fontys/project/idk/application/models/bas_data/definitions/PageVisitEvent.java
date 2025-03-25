package fontys.project.idk.application.models.bas_data.definitions;


import com.fasterxml.jackson.annotation.JsonProperty;
import fontys.project.idk.application.controller.dtos.definitions.PageVisitEventDTO;
import fontys.project.idk.application.models.bas_data.eventPayload.events.Event;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.Map;

@Getter
@Builder
public class PageVisitEvent extends Event {

    public PageVisitEvent() {
        eventName = "page_visit";
    }

    public PageVisitEvent(Data data) {
        eventName = "page_visit";
        this.data = data;
    }
    public PageVisitEvent(String pageType, String pageLanguage, Map<String, Object> additionalData) {
        eventName = "page_visit";
        this.data = new PageVisitEvent.Data(pageType, pageLanguage);
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
