package org.biblio.biblio.services;

import org.biblio.biblio.DTOs.CommentDTO;
import org.biblio.biblio.models.Comment;
import org.biblio.biblio.repositories.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    public List<Comment> getComments() {
        return commentRepository.findAll();
    }

    public List<CommentDTO> getLivreComments(Long livreId) {
        return commentRepository.findByLivreId(livreId).stream().map(c ->
            CommentDTO.builder()
                .id(c.getId())
                .content(c.getContent())
                .user(
                    CommentDTO.UserComment.builder()
                        .imageUrl(c.getUser().getImageUrl())
                        .username(c.getUser().getUsername())
                        .build()
                )
                .build()
        ).toList();
    }

}
