package org.biblio.biblio.DTOs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuteurDTO {

    private Long id;
    private String name;
    private String country;
    private String imageUrl;
    private String description;
    private Double rate;

}
