package org.biblio.biblio.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "livre")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class Livre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String category;
    @ManyToMany
    @JoinTable(
        name = "livre_auteur",
        joinColumns = @JoinColumn(name = "auteur_id"),
        inverseJoinColumns = @JoinColumn(name = "livre_id")
    )
    private List<Auteur> auteurs;
    @OneToMany(mappedBy = "livre", cascade = CascadeType.ALL)
    private List<Emprunt> emprunters;
}
