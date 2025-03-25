package fontys.project.idk.application.models.bas_data.definitions;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@Builder
public class Item {
    @JsonProperty(value = "item_id", required = true)
    int item_id;

    @JsonProperty(value = "item_name", required = true)
    String item_name;

    @JsonProperty(value = "affiliation", required = true)
    String affiliation;

    @JsonProperty(value = "item_brand", required = true)
    String item_brand;

    @JsonProperty(value = "item_category", required = true)
    String item_category;

    @JsonProperty(value = "item_category2", required = true)
    String item_category2;

    @JsonProperty(value = "item_list_id", required = true)
    String item_list_id;

    @JsonProperty(value = "item_list_name", required = true)
    String item_list_name;

    @JsonProperty(value = "index", required = true)
    int index;

    @JsonProperty(value = "item_variant", required = true)
    String item_variant;

    @JsonProperty(value = "price", required = true)
    double price;

    @JsonProperty(value = "quantity", required = true)
    int quantity;
}
