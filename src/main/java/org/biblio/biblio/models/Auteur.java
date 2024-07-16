package org.biblio.biblio.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "Auteur")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class Auteur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String Country;
    @ManyToMany(mappedBy = "auteurs")
    private List<Livre> livres;
}
