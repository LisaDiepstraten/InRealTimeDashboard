package fontys.project.idk.application.dal.entity.eventHeaders;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import jakarta.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "event_headers")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EventHeadersEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="entity", nullable = false, length = 50)
    private String entity;

    @Column(name="entity_key", nullable = false, length = 50)
    private String entityKey;

    @Column(name="event_main_type", nullable = false, length = 50)
    private String eventMainType;

    @Column(name="event_sub_type", nullable = false, length = 50)
    private String eventSubType;

    @JsonProperty(value = "eventTimeStamp", required = true)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name="time_stamp")
    private LocalDateTime eventTimeStamp;

    @Column(name="published_by", nullable = false, length = 50)
    private String publishedBy;

    @Column(name="policy_version", nullable = false, length = 50)
    private String policyVersion;
}
