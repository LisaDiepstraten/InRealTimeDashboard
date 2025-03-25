package fontys.project.idk.application.controller.dtos.eventHeaders;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;

@Getter
@Builder
public class EventHeadersDTO {
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
