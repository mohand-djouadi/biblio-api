package org.biblio.biblio.DTOs;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class RateLivreDTO {

    private Long livreId;
    private double rate;
}
