package fontys.project.idk.application.dal.entity.eventPayload.context.client;

import lombok.*;
import jakarta.persistence.*;

@Entity
@Table(name = "geolocation")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GeolocationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="latitude", length = 100)
    private String latitude;

    @Column(name="longitude", length = 100)
    private String longitude;

    @Column(name="country", length = 100)
    private String country;

    @Column(name="region", length = 100)
    private String region;

    @Column(name="city", length = 100)
    private String city;
}
