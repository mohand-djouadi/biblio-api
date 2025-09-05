package org.biblio.biblio.DTOs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommentDTO {

    private Long id;
    private String content;
    private UserComment user;

    @Data
    @Builder
    public static class UserComment {
        private String username;
        private String imageUrl;
    }

}
