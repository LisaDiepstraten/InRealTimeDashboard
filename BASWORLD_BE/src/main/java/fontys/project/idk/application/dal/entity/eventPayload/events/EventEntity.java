package fontys.project.idk.application.dal.entity.eventPayload.events;

import com.fasterxml.jackson.databind.ObjectMapper;
import fontys.project.idk.application.dal.entity.definitions.ItemEntity;
import lombok.*;
import jakarta.persistence.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) // Change to SINGLE_TABLE
@DiscriminatorColumn(name = "event_type") // This column will hold the type of event
@Table(name="events")
@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class EventEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "event_name", nullable = false, length = 50)
    private String eventName;



    @Convert(converter = JsonAttributeConverter.class)
    @Column(name = "additional_data")
    private Map<String, Object> additionalData = new HashMap<>();

}


// Custom Converter for Map<String, Object>
@Converter
class JsonAttributeConverter implements AttributeConverter<Map<String, Object>, String> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(Map<String, Object> attribute) {
        try {
            return attribute == null ? null : objectMapper.writeValueAsString(attribute);
        } catch (IOException e) {
            throw new RuntimeException("Error converting map to JSON", e);
        }
    }

    @Override
    public Map<String, Object> convertToEntityAttribute(String dbData) {
        try {
            return dbData == null ? null : objectMapper.readValue(dbData, HashMap.class);
        } catch (IOException e) {
            throw new RuntimeException("Error converting JSON to map", e);
        }
    }
}

