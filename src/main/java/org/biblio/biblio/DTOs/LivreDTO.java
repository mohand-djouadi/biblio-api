package org.biblio.biblio.DTOs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LivreDTO {

    private Long id;
    private String title;
    private String category;
    private double rate;
    private Double sellPrice;
    private Double borrowPrice;
    private String description;
    private String imageUrl;
    private String author;
    private List<String> categories;
    private Long likes;

}
