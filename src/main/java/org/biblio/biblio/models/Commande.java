package org.biblio.biblio.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "commande")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Commande {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String status = "en attente récupération";
    @Column(nullable = false)
    private Double totalPrice = 0.0;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    @OneToMany(mappedBy = "commande", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<CommandeLivre> commandes;

    @PrePersist
    public void prePersist() {
        if (status == null) {
            status = "en attente récupération";
        }
        if (totalPrice == null) {
            totalPrice = 0.0;
        }
    }
}
