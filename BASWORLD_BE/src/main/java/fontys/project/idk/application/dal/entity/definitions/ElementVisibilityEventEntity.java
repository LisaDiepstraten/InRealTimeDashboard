package fontys.project.idk.application.dal.entity.definitions;

import com.fasterxml.jackson.annotation.JsonProperty;
import fontys.project.idk.application.dal.entity.eventPayload.events.EventEntity;
import lombok.*;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue("ELEMENT_VISIBILITY_EVENT")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ElementVisibilityEventEntity extends EventEntity {

    @JsonProperty(value = "element_name", required = true)
    private String elementName;
}
