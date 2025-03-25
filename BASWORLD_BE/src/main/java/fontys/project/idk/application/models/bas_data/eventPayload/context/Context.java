package fontys.project.idk.application.models.bas_data.eventPayload.context;


import com.fasterxml.jackson.annotation.JsonProperty;
import fontys.project.idk.application.models.bas_data.eventPayload.context.client.Client;
import fontys.project.idk.application.models.bas_data.eventPayload.context.page.Page;
import fontys.project.idk.application.models.bas_data.eventPayload.context.session.Session;
import fontys.project.idk.application.models.bas_data.eventPayload.context.user.User;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@EqualsAndHashCode
public class Context {
    @JsonProperty(value = "page", required = true)
    Page page;          

    @JsonProperty(value = "session", required = true)
    Session session;    

    @JsonProperty(value = "client", required = true)
    Client client;      

    @JsonProperty(value = "user", required = true)
    User user;          
}
