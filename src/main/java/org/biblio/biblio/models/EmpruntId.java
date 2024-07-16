package org.biblio.biblio.models;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class EmpruntId implements Serializable {

    @Column(name = "utilisateur_id")
    private Long utilisateurId;
    @Column(name = "livre_id")
    private Long livreId;
}
