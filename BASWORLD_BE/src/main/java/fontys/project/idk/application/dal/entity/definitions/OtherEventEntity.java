package fontys.project.idk.application.dal.entity.definitions;

import fontys.project.idk.application.dal.entity.eventPayload.events.EventEntity;
import lombok.*;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue("OTHER_EVENT")
@Getter
@NoArgsConstructor
public class OtherEventEntity extends EventEntity {
    // No additional properties are required
}
