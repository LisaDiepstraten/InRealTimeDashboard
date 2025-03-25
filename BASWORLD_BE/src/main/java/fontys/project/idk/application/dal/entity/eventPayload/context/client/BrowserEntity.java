package fontys.project.idk.application.dal.entity.eventPayload.context.client;

import lombok.*;
import jakarta.persistence.*;

@Entity
@Table(name = "browser")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BrowserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @Column(name="name", length = 100)
    private String name;

    @Column(name="version", length = 100)
    private String version;

    @Column(name="viewport_width")
    private int viewportWidth;

    @Column(name="viewport_height")
    private int viewportHeight;

    @Column(name="language", length = 100)
    private String language;

    @Column(name="cookies_enabled")
    private boolean cookiesEnabled;

    @Column(name="javascript_enabled")
    private boolean javascriptEnabled;

    @Column(name="do_not_track_enabled")
    private boolean doNotTrackEnabled;
}

