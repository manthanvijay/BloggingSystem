package com.wheelseye.Blogging.converter;

import com.wheelseye.Blogging.Entity.Author;
import com.wheelseye.Blogging.Entity.Comment;
import com.wheelseye.Blogging.dto.CommentDTO;

import java.util.Date;

public class CommentConverter {
    public static CommentDTO converter(Comment comment){
        return new CommentDTO().setContent(comment.getContent()).setLikes(comment.getLikes()).setDislikes(comment.getDislikes());
    }
}
