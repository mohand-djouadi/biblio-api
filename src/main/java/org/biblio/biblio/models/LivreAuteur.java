package org.biblio.biblio.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "livre_auteur")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LivreAuteur {

    @EmbeddedId
    private LivreAuteurId id;

    @ManyToOne
    @MapsId("livreId")
    @JoinColumn(name = "livre_id", updatable = false)
    @JsonIgnore
    private Livre livre;

    @ManyToOne
    @MapsId("auteurId")
    @JoinColumn(name = "auteur_id", updatable = false)
    @JsonIgnore
    private Auteur auteur;

}
