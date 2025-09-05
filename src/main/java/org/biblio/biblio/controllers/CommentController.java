package org.biblio.biblio.controllers;

import org.biblio.biblio.DTOs.CommentDTO;
import org.biblio.biblio.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @GetMapping(value = "/{livreId}")
    public List<CommentDTO> getLivreComments(@PathVariable Long livreId) {
        return this.commentService.getLivreComments(livreId);
    }

}
