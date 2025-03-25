package fontys.project.idk.application.models.bas_data.definitions;


import com.fasterxml.jackson.annotation.JsonProperty;
import fontys.project.idk.application.controller.dtos.definitions.ItemDTO;
import fontys.project.idk.application.controller.dtos.definitions.SelectItemEventDTO;
import fontys.project.idk.application.models.bas_data.eventPayload.events.Event;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.Map;


@Getter
@Builder
public class SelectItemEvent extends Event {
    public SelectItemEvent() {
        eventName = "select_item";
    }
    public SelectItemEvent(Data data) {
        eventName = "select_item";
        this.data = data;
    }
    public SelectItemEvent(String item_list_id, String item_list_name, Item item, Map<String, Object> additionalData) {
        eventName = "select_item";
        this.data = new SelectItemEvent.Data(item_list_id, item_list_name, item);
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
        Item item;
    }

    @JsonProperty(value = "data", required = true)
    Data data;
}
