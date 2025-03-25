package fontys.project.idk.application.dal.entity.eventPayload.context.client;

import lombok.*;
import jakarta.persistence.*;

@Entity
@Table(name = "vendor_client_ids")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VendorClientIDsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="google_analytics", length = 100)
    private String googleAnalytics;

    @Column(name="bing", length = 100)
    private String bing;

    @Column(name="facebook", length = 100)
    private String facebook;
}
