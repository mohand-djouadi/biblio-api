package org.biblio.biblio.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "livre")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter @Setter
public class Livre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String category;
    private double rate;
    private int quantity;
    @Column(nullable = true)
    private Double sellPrice;
    @Column(nullable = true)
    private Double borrowPrice;
    @Column(columnDefinition = "TEXT")
    private String description;
    private String imageUrl;
    @Column(nullable = true)
    private Long likes;

    @ManyToMany
    @JoinTable(
        name = "livre_auteur",
        joinColumns = @JoinColumn(name = "auteur_id"),
        inverseJoinColumns = @JoinColumn(name = "livre_id")
    )
    @JsonIgnore
    private List<Auteur> auteurs;

    @OneToMany(mappedBy = "livre", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Emprunt> emprunters;

    @OneToMany(mappedBy = "livre", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<CommandeLivre> livres;
}