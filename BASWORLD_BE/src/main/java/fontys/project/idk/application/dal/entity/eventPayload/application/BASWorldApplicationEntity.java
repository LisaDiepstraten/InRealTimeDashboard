package fontys.project.idk.application.dal.entity.eventPayload.application;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "bas_world_application")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BASWorldApplicationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false, length = 50)
    private String version;

    @Column(nullable = false, length = 50)
    private String environment;
}

