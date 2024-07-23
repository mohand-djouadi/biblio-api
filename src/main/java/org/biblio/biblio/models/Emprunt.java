package org.biblio.biblio.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "emprunt")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class Emprunt {

    @EmbeddedId
    private EmpruntId id;
    @ManyToOne
    @MapsId("utilisateurId")
    @JoinColumn(name = "utilisateur_id", insertable = false, updatable = false)
    @JsonBackReference
    private User utilisateur;
    @ManyToOne
    @MapsId("livreId")
    @JoinColumn(name = "livre_id", insertable = false, updatable = false)
    @JsonBackReference
    private Livre livre;

    private Date startDate;
    private Date endDate;
}
