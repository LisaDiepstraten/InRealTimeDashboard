package fontys.project.idk.application.dal.entity.eventPayload.context.client;

import lombok.*;
import jakarta.persistence.*;

@Entity
@Table(name = "client")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClientEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="butt_client_id", length = 100)
    private String buttClientId;

    @Column(name="raw_user_agent", length = 255)
    private String rawUserAgent;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "vendor_client_ids_id", nullable = false)
    private VendorClientIDsEntity vendorClientIds;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "device_id", nullable = false)
    private DeviceEntity device;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "operating_system_id", nullable = false)
    private OperatingSystemEntity operatingSystem;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "browser_id", nullable = false)
    private BrowserEntity browser;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "engine_id", nullable = false)
    private EngineEntity engine;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "geolocation_id", nullable = false)
    private GeolocationEntity geolocation;
}

