package fontys.project.idk.application.dal.entity;

import fontys.project.idk.application.dal.entity.eventHeaders.EventHeadersEntity;
import fontys.project.idk.application.dal.entity.eventPayload.EventPayloadEntity;
import lombok.*;
import jakarta.persistence.*;

@Entity
@Table(name = "bas_world_event")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BASWorldEventEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 50)
    private String event;

    @JoinColumn(name = "event_payload_id", nullable = false)
    @ManyToOne(cascade = CascadeType.ALL) // Add cascade type
    private EventPayloadEntity eventPayload;

    @JoinColumn(name = "event_header_id", nullable = false)
    @ManyToOne(cascade = CascadeType.ALL) // Add cascade type
    private EventHeadersEntity eventHeaders;
}

