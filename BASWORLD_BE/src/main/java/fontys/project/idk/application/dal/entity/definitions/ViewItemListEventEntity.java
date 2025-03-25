package fontys.project.idk.application.dal.entity.definitions;

import com.fasterxml.jackson.annotation.JsonProperty;
import fontys.project.idk.application.dal.entity.eventPayload.events.EventEntity;
import lombok.*;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@DiscriminatorValue("VIEW_ITEM_LIST_EVENT")
@Getter
@Builder
@NoArgsConstructor
public class ViewItemListEventEntity extends EventEntity {

    // Common properties
    @Column(name = "item_list_id", nullable = false)
    private String itemListID;

    @Column(name = "item_list_name", nullable = false)
    private String itemListName;

    @OneToMany(mappedBy = "viewItemListEvent", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<ItemEntity> items = new HashSet<>();


    // Custom constructor to handle setting bidirectional references
    @Builder
    public ViewItemListEventEntity(String itemListID, String itemListName, Set<ItemEntity> items) {
        this.itemListID = itemListID;
        this.itemListName = itemListName;

        if (items != null) {
            this.items = items;
            // Set the back-reference in each ItemEntity
            for (ItemEntity item : items) {
                item.setViewItemListEvent(this);
            }
        }
    }
}
