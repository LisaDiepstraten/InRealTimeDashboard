package fontys.project.idk.application.dal.entity.eventPayload.context.page;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "page")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "title", nullable = false, length = 255)
    private String title;

    @Column(name = "url", nullable = false, length = 2048)
    private String url;

    @Column(name = "canonical_url", nullable = false, length = 2048)
    private String canonicalUrl;

    @Column(name="design_size_enum_num", nullable = false)
    private int designSize;
}

