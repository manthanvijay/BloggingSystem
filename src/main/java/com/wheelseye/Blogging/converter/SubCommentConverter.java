package com.wheelseye.Blogging.converter;

import com.wheelseye.Blogging.Entity.SubComment;
import com.wheelseye.Blogging.dto.SubCommentDTO;

public class SubCommentConverter {
    public static SubCommentDTO converter(SubComment subComment){
        return new SubCommentDTO().setContent(subComment.getContent()).setLikes(subComment.getLikes()).setDislikes(subComment.getDislikes());
    }
}
