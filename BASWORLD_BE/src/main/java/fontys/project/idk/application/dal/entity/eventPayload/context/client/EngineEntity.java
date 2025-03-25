package fontys.project.idk.application.dal.entity.eventPayload.context.client;

import lombok.*;
import jakarta.persistence.*;

@Entity
@Table(name = "engine")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EngineEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="name", length = 100)
    private String name;

    @Column(name="version", length = 100)
    private String version;
}
