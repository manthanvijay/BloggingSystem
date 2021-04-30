package com.wheelseye.Blogging.request;

import com.wheelseye.Blogging.Entity.Author;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class CreateVlog {
    private String subj;
    private String content;
    private List<String> tags;
    private Date createdAt;
    private Integer authorId;
}
