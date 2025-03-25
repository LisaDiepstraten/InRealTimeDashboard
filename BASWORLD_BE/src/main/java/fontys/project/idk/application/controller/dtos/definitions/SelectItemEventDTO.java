package fontys.project.idk.application.controller.dtos.definitions;


import com.fasterxml.jackson.annotation.JsonProperty;
import fontys.project.idk.application.controller.dtos.eventPayload.events.EventDTO;
import fontys.project.idk.application.models.bas_data.eventPayload.events.Event;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;


@Getter
@Builder
public class SelectItemEventDTO extends EventDTO {
    public SelectItemEventDTO() {
        eventName = "select_item";
    }
    public SelectItemEventDTO(Data data) {
        eventName = "select_item";
        this.data = data;
    }
    public SelectItemEventDTO(String item_list_id, String item_list_name, ItemDTO item, Map<String, Object> additionalData) {
        eventName = "select_item";
        this.data = new Data(item_list_id, item_list_name, item);
        this.additionalData = additionalData;
    }

    @Getter
    @Builder
    public static class Data{
        @JsonProperty(value = "item_list_id", required = true)
        String item_list_id;

        @JsonProperty(value = "item_list_name", required = true)
        String item_list_name;

        @JsonProperty(value = "items", required = true)
        ItemDTO item;
    }

    @JsonProperty(value = "data", required = true)
    Data data;
}
