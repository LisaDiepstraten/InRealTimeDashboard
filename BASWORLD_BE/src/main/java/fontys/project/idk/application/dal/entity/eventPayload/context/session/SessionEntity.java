package fontys.project.idk.application.dal.entity.eventPayload.context.session;

import lombok.*;
import jakarta.persistence.*;

@Entity
@Table(name = "session")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SessionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "butt_session_id", nullable = false, length = 255)
    private String buttSessionId;

    @Column(name = "referer", nullable = false, length = 255)
    private String referer;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "vendor_clicks_id", nullable = false)
    private VendorClicksIDEntity vendorClicksIds;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "utm_parameters_id", nullable = false)
    private UtmParametersEntity utmParameters;
}
