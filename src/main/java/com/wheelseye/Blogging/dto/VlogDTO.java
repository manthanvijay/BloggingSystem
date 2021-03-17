package com.wheelseye.Blogging.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class VlogDTO {
    private String subj;
    private String content;
    private List<String> tags;
    private Integer likes;
    private Integer dislikes;
}
