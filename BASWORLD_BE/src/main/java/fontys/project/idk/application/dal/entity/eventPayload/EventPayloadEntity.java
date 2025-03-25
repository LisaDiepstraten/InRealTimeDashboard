package fontys.project.idk.application.dal.entity.eventPayload;


import fontys.project.idk.application.dal.entity.eventPayload.application.BASWorldApplicationEntity;
import fontys.project.idk.application.dal.entity.eventPayload.context.ContextEntity;
import fontys.project.idk.application.dal.entity.eventPayload.events.EventEntity;
import lombok.*;
import jakarta.persistence.*;

@Entity
@Table(name = "event_payload")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EventPayloadEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "application_id", nullable = false)
    private BASWorldApplicationEntity application;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "context_id", nullable = false)
    private ContextEntity context;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "event_id", nullable = false)
    private EventEntity event;
}
