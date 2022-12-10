package com.parkingcontrolapi.model;

import com.parkingcontrolapi.enums.NomeFuncao;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

@Data
@Entity
@Table(name = "TBL_FUNCAO")
public class Funcao implements GrantedAuthority, Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Enumerated(EnumType.STRING) // -> (cast) Enum <-> String
    @Column(nullable = false, unique = true)
    private NomeFuncao funcao;

    @Override
    public String getAuthority() {
        return this.funcao.toString();
    }
}
