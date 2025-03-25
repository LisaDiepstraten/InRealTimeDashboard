package fontys.project.idk.application.dal.entity.definitions;

import com.fasterxml.jackson.annotation.JsonProperty;
import fontys.project.idk.application.dal.entity.eventPayload.events.EventEntity;
import lombok.*;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue("PAGE_VISIT_EVENT")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PageVisitEventEntity extends EventEntity {

    @JsonProperty(value = "page_type", required = true)
    private String pageType;

    @JsonProperty(value = "page_language", required = true)
    private String pageLanguage;
}
