package fontys.project.idk.application.dal.entity.eventPayload.context;


import fontys.project.idk.application.dal.entity.eventPayload.context.client.ClientEntity;
import fontys.project.idk.application.dal.entity.eventPayload.context.page.PageEntity;
import fontys.project.idk.application.dal.entity.eventPayload.context.session.SessionEntity;
import fontys.project.idk.application.dal.entity.eventPayload.context.user.UserEntity;
import lombok.*;
import jakarta.persistence.*;

@Entity
@Table(name = "context")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContextEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "page_id", nullable = false)
    private PageEntity page;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "session_id", nullable = false)
    private SessionEntity session;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "client_id", nullable = false)
    private ClientEntity client;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;
}

