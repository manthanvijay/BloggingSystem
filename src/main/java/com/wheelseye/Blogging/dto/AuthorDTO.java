package com.wheelseye.Blogging.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)    //ignore what is null
public class AuthorDTO {
    private String name;
    private String dept;
    private String email;
}
