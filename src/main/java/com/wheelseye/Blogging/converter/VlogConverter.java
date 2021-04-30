package com.wheelseye.Blogging.converter;

import com.wheelseye.Blogging.Entity.Vlog;
import com.wheelseye.Blogging.dto.VlogDTO;

public class VlogConverter {
    public static VlogDTO converter(Vlog vlog){
        return new VlogDTO().setContent(vlog.getContent()).setSubj(vlog.getSubj()).setTags(vlog.getTags()).setLikes(vlog.getLikes()).setDislikes(vlog.getDislikes());
    }
}
