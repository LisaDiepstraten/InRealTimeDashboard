package fontys.project.idk.application.dal.entity.eventPayload.context.session;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "vendor_clicks_id")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VendorClicksIDEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "google", nullable = false, length = 255)
    private String google;

    @Column(name = "bing", nullable = false, length = 255)
    private String bing;

    @Column(name = "facebook", nullable = false, length = 255)
    private String facebook;

    @Column(name = "linkedin", nullable = false, length = 255)
    private String linkedIn;
}
