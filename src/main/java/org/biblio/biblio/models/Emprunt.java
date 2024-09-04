package org.biblio.biblio.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
    @JoinColumn(name = "utilisateur_id", updatable = false)
//    @JsonBackReference(value = "user-livre")
    @JsonIgnore
    private User utilisateur;
    @ManyToOne
    @MapsId("livreId")
    @JoinColumn(name = "livre_id", updatable = false)
//    @JsonBackReference(value = "livre-user")
    @JsonIgnore
    private Livre livre;

    private Date startDate;
    private Date endDate;
}
