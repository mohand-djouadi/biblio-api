package org.biblio.biblio.models;

import jakarta.persistence.Embeddable;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Embeddable
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LivreAuteurId {

    private Long livreId;
    private Long auteurId;

}
