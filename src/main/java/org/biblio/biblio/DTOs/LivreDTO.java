package org.biblio.biblio.DTOs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private Long likes;

}
