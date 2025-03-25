package fontys.project.idk.application.dal.entity.definitions;

import com.fasterxml.jackson.annotation.JsonProperty;
import fontys.project.idk.application.dal.entity.eventPayload.events.EventEntity;
import lombok.*;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue("SELECT_ITEM_EVENT")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SelectItemEventEntity extends EventEntity {

    @JsonProperty(value = "item", required = true)
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "item_id", nullable = false)
    private ItemEntity item;

    // Common properties
    @Column(name = "item_list_id", nullable = false)
    private String itemListID;

    @Column(name = "item_list_name", nullable = false)
    private String itemListName;

}
