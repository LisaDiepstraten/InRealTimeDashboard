package fontys.project.idk.application.models.bas_data.eventPayload.context.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@Builder
@EqualsAndHashCode
public class User {
    @JsonProperty(value = "userId",required = true)
    String userId;

    @JsonProperty(value = "userEmailAddress", required = true)
    @Email(message = "Invalid email format.")
    String email;

    @JsonProperty(value = "personId", required = true)
    String personId;

    @JsonProperty(value = "personEmailAddress", required = true)
    @Email(message = "Invalid email format.")
    String personEmail;

    @JsonProperty(value = "companyId", required = true)
    String companyId;

    @JsonProperty(value = "companyName", required = true)
    String companyName;
}
