package com.wheelseye.Blogging.converter;

import com.wheelseye.Blogging.Entity.Merge;
import com.wheelseye.Blogging.dto.MergeDTO;
import lombok.Data;

public class MergeConverter {
    public static MergeDTO converter(Merge merge){
        return new MergeDTO().setAuthorId(merge.getAuthorId()).setDislikes(merge.getDislikes()).setContent(merge.getContent()).setLikes(merge.getLikes()).setParentId(merge.getParentId());
    }
}
