package fontys.project.idk.application.controller.dtos.eventPayload.context.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserDTO {
    @JsonProperty(value = "userId",required = true)
    String userId;

    @JsonProperty(value = "userEmailAddress", required = true)
    @Email(message = "Invalid email format.")
    String userEmailAddress;

    @JsonProperty(value = "personId", required = true)
    String personId;

    @JsonProperty(value = "personEmailAddress", required = true)
    @Email(message = "Invalid email format.")
    String personEmailAddress;

    @JsonProperty(value = "companyId", required = true)
    String companyId;

    @JsonProperty(value = "companyName", required = true)
    String companyName;
}
