package fontys.project.idk.application.controller.dtos.eventPayload.context;


import com.fasterxml.jackson.annotation.JsonProperty;
import fontys.project.idk.application.controller.dtos.eventPayload.context.client.ClientDTO;
import fontys.project.idk.application.controller.dtos.eventPayload.context.page.PageDTO;
import fontys.project.idk.application.controller.dtos.eventPayload.context.session.SessionDTO;
import fontys.project.idk.application.controller.dtos.eventPayload.context.user.UserDTO;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ContextDTO {
    @JsonProperty(value = "page", required = true)
    PageDTO page;

    @JsonProperty(value = "session", required = true)
    SessionDTO session;

    @JsonProperty(value = "client", required = true)
    ClientDTO client;

    @JsonProperty(value = "user", required = true)
    UserDTO user;
}
