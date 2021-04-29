package com.wheelseye.Blogging.request;

import lombok.Data;

import java.util.Date;

@Data
public class CreateSubComment {
    private Integer subCommentId;
    private String content;
    private Date createdAt;
    private Integer commentId;
    private Integer authorId;
}
