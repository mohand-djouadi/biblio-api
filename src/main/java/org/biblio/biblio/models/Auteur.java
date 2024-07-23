package org.biblio.biblio.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
    private String country;
    @ManyToMany(mappedBy = "auteurs")
    @JsonBackReference
    private List<Livre> livres;
}
