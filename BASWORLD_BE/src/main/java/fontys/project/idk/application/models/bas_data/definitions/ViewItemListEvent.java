package fontys.project.idk.application.models.bas_data.definitions;


import com.fasterxml.jackson.annotation.JsonProperty;
import fontys.project.idk.application.controller.dtos.definitions.ItemDTO;
import fontys.project.idk.application.controller.dtos.definitions.ViewItemListEventDTO;
import fontys.project.idk.application.models.bas_data.eventPayload.events.Event;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Map;

@Getter
@Builder
public class ViewItemListEvent extends Event {

    public ViewItemListEvent() {
        eventName = "view_item_list";
    }
    public ViewItemListEvent(Data data) {
        eventName = "view_item_list";
        this.data = data;
    }
    public ViewItemListEvent(String item_list_id, String item_list_name, ArrayList<Item> items, Map<String, Object> additionalData) {
        eventName = "view_item_list";
        this.data = new ViewItemListEvent.Data(item_list_id, item_list_name, items);
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
        ArrayList<Item> items;
    }

    @JsonProperty(value = "data", required = true)
    Data data;
}
