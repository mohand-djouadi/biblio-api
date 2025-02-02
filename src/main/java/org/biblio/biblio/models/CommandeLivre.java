package org.biblio.biblio.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "commande_livre")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class CommandeLivre {

    @EmbeddedId
    private CommandeLivreId id;

    @ManyToOne
    @MapsId("commandeId")
    @JoinColumn(name = "commande_id", updatable = false)
    @JsonIgnore
    private Commande commande;

    @ManyToOne
    @MapsId("livreId")
    @JoinColumn(name = "livre_id", updatable = false)
    @JsonIgnore
    private Livre livre;

    @Column(nullable = true)
    private int quantity;
}
