package fontys.project.idk.application.controller.dtos.definitions;


import com.fasterxml.jackson.annotation.JsonProperty;
import fontys.project.idk.application.controller.dtos.eventPayload.events.EventDTO;
import fontys.project.idk.application.models.bas_data.eventPayload.events.Event;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Map;

@Getter
@Builder
public class ViewItemListEventDTO extends EventDTO {

    public ViewItemListEventDTO() {
        eventName = "view_item_list";
    }
    public ViewItemListEventDTO(Data data) {
        eventName = "view_item_list";
        this.data = data;
    }
    public ViewItemListEventDTO(String item_list_id, String item_list_name, ArrayList<ItemDTO> items, Map<String, Object> additionalData) {
        eventName = "view_item_list";
        this.data = new Data(item_list_id, item_list_name, items);
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
        ArrayList<ItemDTO> items;
    }

    @JsonProperty(value = "data", required = true)
    Data data;
}
