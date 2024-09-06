package org.biblio.biblio.DTOs;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ChangePasswordDTO {

    private String oldPassword;
    private String newPassword;
}
