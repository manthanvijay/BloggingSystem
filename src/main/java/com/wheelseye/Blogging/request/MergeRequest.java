package com.wheelseye.Blogging.request;

import lombok.Data;

import java.util.Date;

@Data
public class MergeRequest {
    private Integer id;
    private Date createdAt;
    private Integer authorId;
    private Integer vlogId;
    private String content;
    private Integer parentId;
}
