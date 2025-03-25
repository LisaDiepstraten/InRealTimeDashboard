package fontys.project.idk.application.dal.entity.eventPayload.context.client;

import lombok.*;
import jakarta.persistence.*;

@Entity
@Table(name = "device")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DeviceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="type", length = 100)
    private String type;

    @Column(name="vendor", length = 100)
    private String vendor;

    @Column(name="model", length = 100)
    private String model;

    @Column(name="screen_width")
    private int screenWidth;

    @Column(name="screen_height")
    private int screenHeight;
}
