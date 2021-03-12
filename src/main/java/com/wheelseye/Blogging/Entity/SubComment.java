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
    @JoinColumn(name = "cmt_id", referencedColumnName = "cmt_id")
    private Comment comment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    private Author author;

    @Column(name = "content")
    private String content;

    @Column(name = "likes")
    private Integer likes;

    @Column(name = "dislikes")
    private Integer dislikes;

    @Column(name ="created_at")
    private Date createdAt;

}
