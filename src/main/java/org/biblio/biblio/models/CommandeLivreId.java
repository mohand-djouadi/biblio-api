package org.biblio.biblio.models;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class CommandeLivreId {

    @Column(name = "commande_Id")
    private Long commandeId;
    @Column(name = "livre_Id")
    private Long livreId;
}
