package fontys.project.idk.application.dal.entity.definitions;

import lombok.*;
import jakarta.persistence.*;

@Entity
@Table(name = "item")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "item_id")
    private int item_id;

    @Column(name = "item_name", nullable = false, length = 50)
    private String item_name;

    @Column(name = "affiliation", nullable = false, length = 50)
    private String affiliation;

    @Column(name = "item_brand", nullable = false, length = 50)
    private String item_brand;

    @Column(name = "item_category", nullable = false, length = 50)
    private String item_category;

    @Column(name = "item_category2", nullable = false, length = 50)
    private String item_category2;

    @Column(name = "item_list_id", nullable = false, length = 50)
    private String item_list_id;

    @Column(name = "item_list_name", nullable = false, length = 50)
    private String item_list_name;

    @Column(name = "item_index", nullable = false)
    private int index;

    @Column(name = "item_variant", nullable = false, length = 50)
    private String item_variant;

    @Column(nullable = false)
    private double price;

    @Column(nullable = false)
    private int quantity;

    @ManyToOne // This annotation defines the relationship
    @JoinColumn(name = "view_item_list_event_id") // Foreign key column
    private ViewItemListEventEntity viewItemListEvent;
}

