package com.wheelseye.Blogging.request;

import lombok.Data;

import javax.persistence.Column;
import java.util.Date;

@Data
public class CreateComment {
    private Integer cmtId;
    private Date createdAt;
    private Integer authorId;
    private Integer vlogId;
    private String content;
}
