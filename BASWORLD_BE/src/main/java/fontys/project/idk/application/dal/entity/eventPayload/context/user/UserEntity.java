package fontys.project.idk.application.dal.entity.eventPayload.context.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "user_id", nullable = false, length = 50)
    private String userId;

    @Column(name = "email", nullable = false, length = 50)
    private String email;

    @Column(name = "person_id", nullable = false, length = 50)
    private String personId;

    @Column(name = "person_email", nullable = false, length = 50)
    private String personEmail;

    @Column(name = "company_id", nullable = false, length = 50)
    private String companyId;

    @Column(name = "company_name", nullable = false, length = 50)
    private String companyName;
}

