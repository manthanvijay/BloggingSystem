package com.wheelseye.Blogging.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class SubCommentDTO {
    private String content;
    private Date createdAt;
    private Integer likes;
    private Integer dislikes;
}
