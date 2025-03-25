package fontys.project.idk.application.models.bas_data.eventHeaders;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;

@Getter
@Builder
@EqualsAndHashCode
public class EventHeaders {
    @JsonProperty(value = "entity", required = true)
    String entity;

    @JsonProperty(value = "entityKey", required = true)
    String entityKey;

    @JsonProperty(value = "eventMainType", required = true)
    String eventMainType;

    @JsonProperty(value = "eventSubType", required = true)
    String eventSubType;

    @JsonProperty(value = "eventTimeStamp", required = true)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Europe/Amsterdam")
    LocalDateTime eventTimeStamp;

    @JsonProperty(value = "publishedBy", required = true)
    String publishedBy;

    @JsonProperty(value = "policyVersion", required = true)
    String policyVersion;
}
