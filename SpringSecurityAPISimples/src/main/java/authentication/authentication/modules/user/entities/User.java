package authentication.authentication.modules.user.entities;

import java.util.List;
import java.util.UUID;
import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

  @Id
  @GeneratedValue
  private UUID id;

  private String name;

  private String username;

  private String password;

  @ManyToMany
  private List<Role> roles;
}
