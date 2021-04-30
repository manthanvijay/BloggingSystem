package com.wheelseye.Blogging.Entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "sub_comments")
@Getter @Setter
public class SubComment implements Serializable
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "sub_cmt_id", unique = true, nullable = false)
    private Integer subCommentId;

    @ManyToOne
    @JoinColumn(name = "cmt_id")
    private Comment comment;

    @Column(name ="cmt_id", insertable = false, updatable = false)
    private Integer cmtId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    private Author author;

    @Column(name ="author_id", insertable = false, updatable = false)
    private Integer authorId;

    @Column(name = "content")
    private String content;

    @Column(name = "likes")
    private Integer likes=0;

    @Column(name = "dislikes")
    private Integer dislikes=0;

    @Column(name ="created_at")
    private Date createdAt;

}
