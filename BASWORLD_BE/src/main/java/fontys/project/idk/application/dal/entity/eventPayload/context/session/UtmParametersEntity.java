package fontys.project.idk.application.dal.entity.eventPayload.context.session;

import lombok.*;
import jakarta.persistence.*;

@Entity
@Table(name = "utm_parameters")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UtmParametersEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "source", nullable = false, length = 255)
    private String source;

    @Column(name = "medium", nullable = false, length = 255)
    private String medium;

    @Column(name = "campaign", nullable = false, length = 255)
    private String campaign;

    @Column(name = "term", nullable = false, length = 255)
    private String term;

    @Column(name = "content", nullable = false, length = 255)
    private String content;
}
